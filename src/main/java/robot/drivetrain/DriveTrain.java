package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.ARB_FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT;
import static robot.drivetrain.DriveTrainConstants.PRIMARY_PID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  private final BasicDriveTrainComponents components;

  public DriveTrain(final BasicDriveTrainComponents components) {
    this.components = components;
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

  public TalonFX getLeftMaster() {
    return components.getLeftMasterMotor();
  }

  public TalonFX getRightMaster() {
    return components.getRightMasterMotor();
  }

  public void moveMotorByMotionMagic(final TalonFX motor, final double distance) {
    motor.selectProfileSlot(DRIVE_BY_DISTANCE_SLOT, PRIMARY_PID);
    motor.set(ControlMode.MotionMagic, cmToEncoderUnits(distance) + motor.getSelectedSensorPosition(),
        DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
  }

  public boolean isDriveOnTarget() {
    return Math.abs(components.getLeftMasterMotor().getClosedLoopError()) < DriveTrainConstants.TOLERANCE &&
        Math.abs(components.getRightMasterMotor().getClosedLoopError()) < DriveTrainConstants.TOLERANCE;
  }

  public Pose2d getPose() {
    return components.getOdometry().getPoseMeters();
  }

  public void tankDriveVolts(final double rightVolts, final double leftVolts) {
    components.getRightMasterMotor().setVoltage(rightVolts);
    components.getLeftMasterMotor().setVoltage(leftVolts);
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoder().getRate(), getRightEncoder().getRate());
  }

  private double getLeftDistance() {
    return components.getLeftMasterMotor().getSelectedSensorPosition() / DriveTrainConstants.ENCODER_UNITS *
        DriveTrainConstants.PERIMETER;
  }

  private double getRightDistance() {
    return components.getRightMasterMotor().getSelectedSensorPosition() / DriveTrainConstants.ENCODER_UNITS *
        DriveTrainConstants.PERIMETER;
  }

  private TalonFXEncoder getLeftEncoder() {
    return new TalonFXEncoder(components.getLeftMasterMotor(), 0);
  }

  private TalonFXEncoder getRightEncoder() {
    return new TalonFXEncoder(components.getRightMasterMotor(), 0);
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
