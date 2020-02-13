package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.ARB_FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_FORWARD_SENSITIVITY;
import static robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_ROTATION_SENSITIVITY;
import static robot.drivetrain.DriveTrainConstants.CM_TO_METERS;
import static robot.drivetrain.DriveTrainConstants.CONVERSION_RATE;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.ODOMETRY_TARGET_ANGLE;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.ODOMETRY_TARGET_X;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.ODOMETRY_TARGET_Y;
import static robot.drivetrain.DriveTrainConstants.PATHS;
import static robot.drivetrain.DriveTrainConstants.PERIMETER;
import static robot.drivetrain.DriveTrainConstants.PERIMETER_IN_METERS;
import static robot.drivetrain.DriveTrainConstants.PRIMARY_PID;
import static robot.drivetrain.DriveTrainConstants.SEC_TO_100MS;
import static robot.drivetrain.DriveTrainConstants.TOLERANCE;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.DEGREES_IN_FULL_ROTATION;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.ENCODER_CPR;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.TRAJECTORY_PID_SLOT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

public class DriveTrain extends SubsystemBase {

  private final BasicDriveTrainComponents components;
  private final SendableChooser<Integer> pathChooser = new SendableChooser<>();


  public DriveTrain(final BasicDriveTrainComponents components) {
    this.components = components;
    resetEncoders();

    pathChooser.setDefaultOption("Auto1", 1);
    pathChooser.addOption("Auto2", 2);
    pathChooser.addOption("Auto3", 3);
    pathChooser.addOption("Auto4", 4);
    pathChooser.addOption("Auto5", 5);
    Shuffleboard.enableActuatorWidgets();

    Shuffleboard.getTab("Odometry").add("Target X:", 1).getEntry().addListener(
        x -> setOdometryTargetX(x.value.getDouble()), EntryListenerFlags.kUpdate);
    Shuffleboard.getTab("Odometry").add("Target Y:", 0).getEntry().addListener(
        y -> setOdometryTargetY(y.value.getDouble()), EntryListenerFlags.kUpdate);
    Shuffleboard.getTab("Odometry").add("Target Angle:", 0).getEntry().addListener(
        angle -> setOdometryTargetAngle(angle.value.getDouble()), EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Odometry").add("ComboBox", pathChooser).withWidget(BuiltInWidgets.kComboBoxChooser);
    System.out.println(pathChooser.getSelected());
  }

  @Override
  public void periodic() {
    components.getOdometry().update(Rotation2d.fromDegrees(getOdometryHeading()),
        getLeftDistance() / CM_TO_METERS, getRightDistance() / CM_TO_METERS);
  }

  public void stopDrive() {
    components.getDifferentialDrive().stopMotor();
  }

  public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
    components.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
        rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
  }

  public void driveByMotionMagic(final double leftTarget, final double rightTarget) {
    moveMotorByMotionMagic(getLeftMaster(), leftTarget);
    moveMotorByMotionMagic(getRightMaster(), rightTarget);
  }

  public Pose2d getPose() {
    return components.getOdometry().getPoseMeters();
  }

  public Pose2d getTargetPose() {
    return new Pose2d(ODOMETRY_TARGET_X, ODOMETRY_TARGET_Y, Rotation2d.fromDegrees(ODOMETRY_TARGET_ANGLE));
  }

  public boolean isDriveOnTarget(final double leftTarget, final double rightTarget) {
    return Math.abs(leftTarget - getLeftMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE) &&
        Math.abs(rightTarget - getRightMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE);
  }

  public void driveTrainVelocity(final double leftVelocity, final double rightVelocity) {
    final double leftFeedForwardVolts = FEED_FORWARD.calculate(leftVelocity, 0);
    final double rightFeedForwardVolts = FEED_FORWARD.calculate(rightVelocity, 0);

    getLeftMaster().selectProfileSlot(TRAJECTORY_PID_SLOT, 0);
    getRightMaster().selectProfileSlot(TRAJECTORY_PID_SLOT, 0);
    getLeftMaster().set(ControlMode.Velocity, metersPerSecToStepsPer100ms(leftVelocity),
        DemandType.ArbitraryFeedForward, leftFeedForwardVolts / RobotController.getBatteryVoltage());
    getRightMaster().set(ControlMode.Velocity, metersPerSecToStepsPer100ms(rightVelocity),
        DemandType.ArbitraryFeedForward, rightFeedForwardVolts / RobotController.getBatteryVoltage());
  }

  public double getRightTargetFromDistance(final double distance) {
    return getTargetFromDistance(getRightMaster(), distance);
  }

  public double getLeftTargetFromDistance(final double distance) {
    return getTargetFromDistance(getLeftMaster(), distance);
  }

  public List<Pose2d> getPath() {
    if (getAutonomousPath() > 5 || getAutonomousPath() < 1)
      return getPoseFromVision();
    return PATHS.get(getAutonomousPath());
  }

  private void moveMotorByMotionMagic(final TalonFX motor, final double target) {
    motor.selectProfileSlot(DRIVE_BY_DISTANCE_SLOT, PRIMARY_PID);
    motor.set(ControlMode.MotionMagic, target, DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
  }

  private double getTargetFromDistance(final TalonFX motor, final double distance) {
    return cmToEncoderUnits(distance) + motor.getSelectedSensorPosition();
  }

  private WPI_TalonFX getLeftMaster() {
    return components.getLeftMasterMotor();
  }

  private WPI_TalonFX getRightMaster() {
    return components.getRightMasterMotor();
  }

  private double getLeftDistance() {
    return ((double) getLeftMaster().getSelectedSensorPosition()) / ENCODER_CPR * PERIMETER;
  }

  private double getRightDistance() {
    return ((double) getRightMaster().getSelectedSensorPosition() / ENCODER_CPR) * PERIMETER;
  }

  private double getOdometryHeading() {
    return Math.IEEEremainder(components.getPigeonYaw(), DEGREES_IN_FULL_ROTATION);
  }

  private void resetOdometryToPose(final Pose2d pose) {
    resetEncoders();
    components.getOdometry().resetPosition(pose, Rotation2d.fromDegrees(getOdometryHeading()));
  }

  private int getAutonomousPath() {
    return pathChooser.getSelected();
  }

  private List<Pose2d> getPoseFromVision() {
    return List.of(new Pose2d());
  }

  private void setOdometryTargetX(final double x) {
    ODOMETRY_TARGET_X = x;
  }

  private void setOdometryTargetY(final double y) {
    ODOMETRY_TARGET_Y = y;
  }

  private void setOdometryTargetAngle(final double angle) {
    ODOMETRY_TARGET_ANGLE = angle;
  }

  private double cmToEncoderUnits(final double cm) {
    return CONVERSION_RATE * ENCODER_CPR * cm / PERIMETER;
  }

  private double metersToSteps(final double meters) {
    return (ENCODER_CPR / PERIMETER_IN_METERS) * meters;
  }

  private double metersPerSecToStepsPer100ms(final double metersPerSec) {
    return metersToSteps(metersPerSec / SEC_TO_100MS);
  }

  private void resetEncoders() {
    getLeftMaster().setSelectedSensorPosition(0);
    getRightMaster().setSelectedSensorPosition(0);
  }
}
