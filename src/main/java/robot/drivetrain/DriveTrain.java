package robot.drivetrain;

import static robot.RobotConstants.PRIMARY_PID;
import static robot.drivetrain.DriveTrainConstants.ARB_FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_FORWARD_SENSITIVITY;
import static robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_ROTATION_SENSITIVITY;
import static robot.drivetrain.DriveTrainConstants.CM_TO_METERS;
import static robot.drivetrain.DriveTrainConstants.CONVERSION_RATE;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.ENCODER_CPR;
import static robot.drivetrain.DriveTrainConstants.PERIMETER;
import static robot.drivetrain.DriveTrainConstants.PERIMETER_IN_METERS;
import static robot.drivetrain.DriveTrainConstants.SEC_TO_100MS;
import static robot.drivetrain.DriveTrainConstants.TARGET_POSE;
import static robot.drivetrain.DriveTrainConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

public class DriveTrain extends SubsystemBase {

  private final SendableChooser<Integer> pathChooser = new SendableChooser<>();
  private final DriveTrainComponents components;

  public DriveTrain(final DriveTrainComponents components) {
    this.components = components;
    Shuffleboard.getTab("Odometry").addNumber("X",
        () -> components.getOdometry().getPoseMeters().getTranslation().getX());
    Shuffleboard.getTab("Odometry").addNumber("Y",
        () -> components.getOdometry().getPoseMeters().getTranslation().getY());
    Shuffleboard.getTab("Odometry").addNumber("Heading",
        () -> components.getOdometry().getPoseMeters().getRotation().getDegrees());
    resetEncoders();
  }

  @Override
  public void periodic() {
    components.getOdometry().update(Rotation2d.fromDegrees(-getOdometryHeading()),
        getLeftDistance() / CM_TO_METERS, getRightDistance() / CM_TO_METERS);
  }

  public void initMotionProfileSlot(final int slot) {
    components.getLeftMasterMotor().selectProfileSlot(slot, PRIMARY_PID);
    components.getRightMasterMotor().selectProfileSlot(slot, PRIMARY_PID);
  }

  public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
    components.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
        rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, true);
  }

  public double getAngleToTargetFromCurrentPose() {
    return Math.abs(getPose().getRotation().getDegrees() - Math.toDegrees(Math.atan2(getPose().getTranslation().getY() - TARGET_POSE.getTranslation().getY(),
        getPose().getTranslation().getX() - TARGET_POSE.getTranslation().getX()))) - TARGET_POSE.getRotation().getDegrees();
  }

  public Pose2d getPose() {
    return components.getOdometry().getPoseMeters();
  }

  public Pose2d getReversedPose() {
    return new Pose2d(getPose().getTranslation().getX(), getPose().getTranslation().getY(),
        Rotation2d.fromDegrees(180 - getPose().getRotation().getDegrees()));
  }

  public boolean isDriveOnTarget(final double leftTarget, final double rightTarget) {
    return Math.abs(leftTarget - getLeftMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE) &&
        Math.abs(rightTarget - getRightMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE);
  }

  public void driveByMotionMagic(final double leftTarget, final double rightTarget) {
    driveMotorByMotionMagic(getLeftMaster(), leftTarget);
    driveMotorByMotionMagic(getRightMaster(), rightTarget);
  }

  public void tankDriveVolts(final double leftVoltage, final double rightVoltage) {
    getLeftMaster().setVoltage(leftVoltage);
    getRightMaster().setVoltage(rightVoltage);
  }

  public DifferentialDriveKinematics getKinematics() {
    return components.getDriveKinematics();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftMaster().getSelectedSensorVelocity(),
        getRightMaster().getSelectedSensorVelocity());
  }

  public SimpleMotorFeedforward getFeedForward() {
    return components.getMotorFeedForward();
  }

  public double getRightTargetFromDistance(final double distance) {
    return getTargetFromDistance(getRightMaster(), distance);
  }

  public double getLeftTargetFromDistance(final double distance) {
    return getTargetFromDistance(getLeftMaster(), distance);
  }

  public void stopDrive() {
    components.getDifferentialDrive().stopMotor();
  }

  public DriveTrainComponents getComponents() {
    return components;
  }

  public double getOdometryHeading() {
    return components.getPigeonIMU().getRawYaw();
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

  public OnyxTrajectoryGenerator getTrajectoryGenerator() {
    return components.getTrajectoryGenerator();
  }

  public void setGyroAngle(double angle) {
    components.getPigeonIMU().setYaw(angle);
  }

  public void resetOdometryToPose(final Pose2d pose) {
    resetEncoders();
    components.getOdometry().resetPosition(pose, Rotation2d.fromDegrees(getOdometryHeading()));
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
