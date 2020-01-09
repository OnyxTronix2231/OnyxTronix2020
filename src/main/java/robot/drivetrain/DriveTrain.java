package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import sensors.counter.TalonSrxEncoder;

public class DriveTrain extends SubsystemBase {

  private final BasicDriveTrainComponents components;

  public DriveTrain(final BasicDriveTrainComponents components) {
    this.components = components;
    CommandScheduler.getInstance().registerSubsystem(this);
  }

  @Override
  public void periodic() {
    components.getOdometry().update(Rotation2d.fromDegrees(components.getOdometryHeading()), getLeftDistance(), getRightDistance());
  }

  public final void stopDrive() {
    components.getDifferentialDrive().stopMotor();
  }

  public final void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
    components.getDifferentialDrive().arcadeDrive(forwardSpeed, rotationSpeed);
  }

  public final void initializeDriveByDistance(final double distance) {
    components.getLeftMasterMotor().selectProfileSlot(DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT,
        DriveTrainConstants.PRIMARY_PID);
    components.getLeftMasterMotor().set(ControlMode.MotionMagic, cmToEncoderUnits(distance)
            + components.getLeftMasterMotor().getSelectedSensorPosition(),
        DemandType.ArbitraryFeedForward, DriveTrainConstants.ARB_FEED_FORWARD);

    components.getRightMasterMotor().selectProfileSlot(DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT,
        DriveTrainConstants.PRIMARY_PID);
    components.getRightMasterMotor().set(ControlMode.MotionMagic, cmToEncoderUnits(distance)
            + components.getRightMasterMotor().getSelectedSensorPosition(),
        DemandType.ArbitraryFeedForward, DriveTrainConstants.ARB_FEED_FORWARD);
  }

  public final double getLeftDistance() {
    return components.getLeftMasterMotor().getSelectedSensorPosition() / DriveTrainConstants.ENCODER_UNITS *
        DriveTrainConstants.PERIMETER;
  }

  public final double getRightDistance() {
    return components.getRightMasterMotor().getSelectedSensorPosition() / DriveTrainConstants.ENCODER_UNITS *
        DriveTrainConstants.PERIMETER;
  }

  public final boolean isDriveOnTarget() {
    return Math.abs(components.getLeftMasterMotor().getClosedLoopError()) < DriveTrainConstants.TOLERANCE &&
        Math.abs(components.getRightMasterMotor().getClosedLoopError()) < DriveTrainConstants.TOLERANCE;
  }

  public final Pose2d getPose() {
    return components.getOdometry().getPoseMeters();
  }

  public final void tankDriveVolts(final double rightVolts, final double leftVolts) {
    components.getRightMasterMotor().setVoltage(rightVolts);
    components.getLeftMasterMotor().setVoltage(leftVolts);
  }

  public final DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoder().getRate(), getRightEncoder().getRate());
  }

  private TalonSrxEncoder getLeftEncoder() {
    return new TalonSrxEncoder(components.getLeftMasterMotor(), 0);
  }

  private TalonSrxEncoder getRightEncoder() {
    return new TalonSrxEncoder(components.getRightMasterMotor(), 0);
  }

  private void resetEncoders() {
    getLeftEncoder().reset();
    getRightEncoder().reset();
  }

  private void resetOdometry(final Pose2d pose) {
    resetEncoders();
    components.getOdometry().resetPosition(pose, Rotation2d.fromDegrees(components.getOdometryHeading()));
  }

  private double cmToEncoderUnits(final double cm) {
    return DriveTrainConstants.ENCODER_UNITS / (cm / (DriveTrainConstants.PERIMETER));
  }

}