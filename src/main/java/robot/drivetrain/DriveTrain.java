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
import static robot.drivetrain.DriveTrainConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

    Shuffleboard.getTab("Odometry").addNumber("Yaw angle", () -> getRawRobotHeading());
    pathChooser.setDefaultOption("Path 1", 1);
    pathChooser.addOption("Path 2", 2);
    pathChooser.addOption("Path 3", 3);
    pathChooser.addOption("Path 4", 4);
    pathChooser.addOption("Path 5", 5);

    Shuffleboard.getTab("Odometry").add("ComboBox", pathChooser).withWidget(BuiltInWidgets.kComboBoxChooser);
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

  public void driveByMotionMagic(final double leftTarget, final double rightTarget) {
    driveMotorByMotionMagic(getLeftMaster(), leftTarget);
    driveMotorByMotionMagic(getRightMaster(), rightTarget);
  }

  public Pose2d getPose() {
    return components.getOdometry().getPoseMeters();
  }

  public boolean isDriveOnTarget(final double leftTarget, final double rightTarget) {
    return Math.abs(leftTarget - getLeftMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE) &&
        Math.abs(rightTarget - getRightMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE);
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

  public List<Pose2d> getPath() {
    if (getAutonomousPath() > 5 || getAutonomousPath() < 1)
      return getPoseFromVision();
    return PATHS.get(getAutonomousPath());
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

  public void setNeutralModeToCoast() {
    components.getLeftMasterMotor().setNeutralMode(NeutralMode.Coast);
    components.getLeftSlaveMotor().setNeutralMode(NeutralMode.Coast);
    components.getRightMasterMotor().setNeutralMode(NeutralMode.Coast);
    components.getRightSlaveMotor().setNeutralMode(NeutralMode.Coast);
  }

  public void setNeutralModeToBrake() {
    components.getLeftMasterMotor().setNeutralMode(NeutralMode.Brake);
    components.getLeftSlaveMotor().setNeutralMode(NeutralMode.Brake);
    components.getRightMasterMotor().setNeutralMode(NeutralMode.Brake);
    components.getRightSlaveMotor().setNeutralMode(NeutralMode.Brake);
  }

  public void setGyroAngle(double angle) {
    components.getPigeonIMU().setYaw(angle);
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

  private List<Pose2d> getPoseFromVision() {//For future Vision integration - will delete comment pre-merge
    return List.of(new Pose2d());
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
