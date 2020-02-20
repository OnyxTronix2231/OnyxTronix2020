package robot.drivetrain;

import static robot.RobotConstants.PRIMARY_PID;
import static robot.drivetrain.DriveTrainConstants.ARB_FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_FORWARD_SENSITIVITY;
import static robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_ROTATION_SENSITIVITY;
import static robot.drivetrain.DriveTrainConstants.CM_TO_METERS;
import static robot.drivetrain.DriveTrainConstants.CONVERSION_RATE;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.ENCODER_CPR;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.TRAJECTORY_PID_SLOT;
import static robot.drivetrain.DriveTrainConstants.PERIMETER;
import static robot.drivetrain.DriveTrainConstants.PERIMETER_IN_METERS;
import static robot.drivetrain.DriveTrainConstants.Paths.PATHS;
import static robot.drivetrain.DriveTrainConstants.SEC_TO_100MS;
import static robot.drivetrain.DriveTrainConstants.TARGET_POSE;
import static robot.drivetrain.DriveTrainConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

public class DriveTrain extends SubsystemBase {

  private final SendableChooser<Integer> pathChooser = new SendableChooser<>();
  private final DriveTrainComponents components;

  public DriveTrain(final DriveTrainComponents components) {
    this.components = components;
    resetEncoders();

    pathChooser.setDefaultOption("Path 1", 1);
    pathChooser.addOption("Path 2", 2);
    pathChooser.addOption("Path 3", 3);
    pathChooser.addOption("Path 4", 4);
    pathChooser.addOption("Path 5", 5);
    Shuffleboard.enableActuatorWidgets();

    Shuffleboard.getTab("Odometry").add("ComboBox", pathChooser).withWidget(BuiltInWidgets.kComboBoxChooser);
    System.out.println(pathChooser.getSelected());
  }

  @Override
  public void periodic() {
    components.getOdometry().update(Rotation2d.fromDegrees(getOdometryHeading()),
        getLeftDistance() / CM_TO_METERS, getRightDistance() / CM_TO_METERS);
  }

  public void initMotionProfileSlot(final int slot) {
    components.getLeftMasterMotor().selectProfileSlot(slot, PRIMARY_PID);
    components.getRightMasterMotor().selectProfileSlot(slot, PRIMARY_PID);
  }

  public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
    components.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
        rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
  }

  public double getAngleToTargetFromCurrentPose() {
    return getPose().relativeTo(TARGET_POSE).getRotation().getDegrees();
  }

  public Pose2d getPose() {
    return components.getOdometry().getPoseMeters();
  }

  public Pose2d getAngleReversedPose() {
    return new Pose2d(getPose().getTranslation().getX(), getPose().getTranslation().getY(),
        Rotation2d.fromDegrees(-getPose().getRotation().getDegrees()));
  }

  public boolean isDriveOnTarget(final double leftTarget, final double rightTarget) {
    return Math.abs(leftTarget - getLeftMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE) &&
        Math.abs(rightTarget - getRightMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE);
  }

  public void driveByMotionMagic(final double leftTarget, final double rightTarget) {
    driveMotorByMotionMagic(getLeftMaster(), leftTarget);
    driveMotorByMotionMagic(getRightMaster(), rightTarget);
  }

  public void driveTrainVelocity(final double leftVelocity, final double rightVelocity) {
    final double leftFeedForwardVolts = components.getMotorFeedForward().calculate(leftVelocity, 0);
    final double rightFeedForwardVolts = components.getMotorFeedForward().calculate(rightVelocity, 0);

    initMotionProfileSlot(TRAJECTORY_PID_SLOT);
    getLeftMaster().set(ControlMode.Velocity, metersPerSecToStepsPer100ms(leftVelocity),
        DemandType.ArbitraryFeedForward, leftFeedForwardVolts / RobotController.getBatteryVoltage());
    getRightMaster().set(ControlMode.Velocity, metersPerSecToStepsPer100ms(rightVelocity),
        DemandType.ArbitraryFeedForward, rightFeedForwardVolts / RobotController.getBatteryVoltage());
  }

  public void driveTrainVelocityReverse(final double leftVelocity, final double rightVelocity) {
    driveTrainVelocity(-leftVelocity, -rightVelocity);
  }

  public double getRightTargetFromDistance(final double distance) {
    return getTargetFromDistance(getRightMaster(), distance);
  }

  public double getLeftTargetFromDistance(final double distance) {
    return getTargetFromDistance(getLeftMaster(), distance);
  }

  public Path[] getFullAutonomousPath() {
    if (getAutonomousPath() > PATHS.length || getAutonomousPath() <= 0)
      return new Path[]{new Path(true, List.of(getPoseFromVision()))};
    return PATHS[getAutonomousPath() - 1];
  }

  public void stopDrive() {
    components.getDifferentialDrive().stopMotor();
  }

  public DriveTrainComponents getComponents() {
    return components;
  }

  public double getOdometryHeading() {
    return components.getPigeonIMU().getNormalizedYaw();
  }

  public double getRawRobotHeading() {
    return components.getPigeonIMU().getRawYaw();
  }

  private void driveMotorByMotionMagic(final TalonFX motor, final double target) {
    motor.set(ControlMode.MotionMagic, target, DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
  }

  private WPI_TalonFX getLeftMaster() {
    return components.getLeftMasterMotor();
  }

  private WPI_TalonFX getRightMaster() {
    return components.getRightMasterMotor();
  }

  private double getTargetFromDistance(final TalonFX motor, final double distance) {
    return cmToEncoderUnits(distance) + motor.getSelectedSensorPosition();
  }

  private double getLeftDistance() {
    return ((double) getLeftMaster().getSelectedSensorPosition()) / ENCODER_CPR * PERIMETER;
  }

  private double getRightDistance() {
    return ((double) getRightMaster().getSelectedSensorPosition() / ENCODER_CPR) * PERIMETER;
  }

  private void resetOdometryToPose(final Pose2d pose) {//For future Vision integration - will delete comment pre-merge
    resetEncoders();
    components.getOdometry().resetPosition(pose, Rotation2d.fromDegrees(getOdometryHeading()));
  }

  private int getAutonomousPath() {
    return pathChooser.getSelected();
  }

  private Pose2d getPoseFromVision() {//For future Vision integration - will delete comment pre-merge
    return null;
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
