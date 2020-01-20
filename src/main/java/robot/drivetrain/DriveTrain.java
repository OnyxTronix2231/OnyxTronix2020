package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.ARB_FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.CONVERSION_RATE;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT;
import static robot.drivetrain.DriveTrainConstants.ENCODER_UNITS;
import static robot.drivetrain.DriveTrainConstants.PERIMETER;
import static robot.drivetrain.DriveTrainConstants.PRIMARY_PID;
import static robot.drivetrain.DriveTrainConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  private final BasicDriveTrainComponents components;

  public DriveTrain(final BasicDriveTrainComponents components) {
    this.components = components;
    resetEncoders();
    resetOdometry(new Pose2d());
  }

  @Override
  public void periodic() {
    components.getOdometry().update(Rotation2d.fromDegrees(components.getOdometryHeading()), getLeftDistance(), getRightDistance());
  }

  public void stopDrive() {
    components.getDifferentialDrive().stopMotor();
  }

  public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
    components.getDifferentialDrive().arcadeDrive(forwardSpeed, rotationSpeed);
  }

  public void driveByMotionMagic(final double leftTarget, final double rightTarget) {
    moveMotorByMotionMagic(getLeftMaster(), leftTarget);
    moveMotorByMotionMagic(getRightMaster(), rightTarget);
  }

  public void print(final double leftTarget, final double rightTarget) {
    System.out.println("Left Distance = " + (getLeftDistance()));
    System.out.println("Right Distance = " + (getLeftMaster().getSelectedSensorPosition()));
    System.out.println(leftTarget);
    System.out.println(rightTarget);
  }

  public double getLeftDistance() {
    return getLeftMaster().getSelectedSensorPosition() / ENCODER_UNITS * PERIMETER;
  }

  public Pose2d getPose() {
    return components.getOdometry().getPoseMeters();
  }

  public boolean isDriveOnTarget(final double leftTarget, final double rightTarget) {
    return Math.abs(leftTarget - getLeftMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE) &&
        Math.abs(rightTarget - getRightMaster().getSelectedSensorPosition()) < cmToEncoderUnits(TOLERANCE);
  }

  public void tankDriveVolts(final double rightVolts, final double leftVolts) {
    getLeftMaster().setVoltage(leftVolts);
    getRightMaster().setVoltage(rightVolts);
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoder().getRate(), getRightEncoder().getRate());
  }

  public double getRightTargetFromDistance(final double distance) {
    return getTargetFromDistance(getRightMaster(), distance);
  }

  public double getLeftTargetFromDistance(final double distance) {
    return getTargetFromDistance(getLeftMaster(), distance);
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

  private double getRightDistance() {
    return getRightMaster().getSelectedSensorPosition() / ENCODER_UNITS * PERIMETER;
  }

  private TalonFXEncoder getLeftEncoder() {
    return new TalonFXEncoder(components.getLeftMasterMotor(), PRIMARY_PID);
  }

  private TalonFXEncoder getRightEncoder() {
    return new TalonFXEncoder(components.getRightMasterMotor(), PRIMARY_PID);
  }

  private void resetOdometry(final Pose2d pose) {
    resetEncoders();
    components.getOdometry().resetPosition(pose, Rotation2d.fromDegrees(components.getOdometryHeading()));
  }

  private double cmToEncoderUnits(final double cm) {
    return CONVERSION_RATE * ENCODER_UNITS * cm / PERIMETER;
  }

  public void resetEncoders() {
    getLeftMaster().setSelectedSensorPosition(0);
    getRightMaster().setSelectedSensorPosition(0);
    getLeftEncoder().reset();
    getRightEncoder().reset();
  }
}
