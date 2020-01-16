package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.ARB_FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT;
import static robot.drivetrain.DriveTrainConstants.ENCODER_UNITS;
import static robot.drivetrain.DriveTrainConstants.PERIMETER;
import static robot.drivetrain.DriveTrainConstants.PRIMARY_PID;
import static robot.drivetrain.DriveTrainConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.drivetrain.commands.DriveByDistance;
import robot.drivetrain.commands.DriveBySpeed;

public class DriveTrain extends SubsystemBase {

  private final BasicDriveTrainComponents components;

  public DriveTrain(final BasicDriveTrainComponents components) {
    this.components = components;
  }

  public void stopDrive() {
    components.getDifferentialDrive().stopMotor();
  }

  public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
    components.getDifferentialDrive().arcadeDrive(forwardSpeed, rotationSpeed);
  }

  public void driveByMotionMagic(final double distance) {
    moveMotorByMotionMagic(getLeftMaster(), distance);
    moveMotorByMotionMagic(getRightMaster(), distance);
  }

  public double getLeftDistance() {
    return getLeftMaster().getSelectedSensorPosition() / ENCODER_UNITS * PERIMETER;
  }

  public double getRightDistance() {
    return getRightMaster().getSelectedSensorPosition() / ENCODER_UNITS * PERIMETER;
  }

  public boolean isDriveOnTarget() {
    return Math.abs(getLeftMaster().getClosedLoopError()) < TOLERANCE &&
        Math.abs(getRightMaster().getClosedLoopError()) < TOLERANCE;
  }

  private void moveMotorByMotionMagic(final TalonFX motor, final double distance) {
    motor.selectProfileSlot(DRIVE_BY_DISTANCE_SLOT, PRIMARY_PID);
    motor.set(ControlMode.MotionMagic, cmToEncoderUnits(distance) + motor.getSelectedSensorPosition(),
        DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
  }

  private TalonFX getLeftMaster() {
    return components.getLeftMasterMotor();
  }

  private TalonFX getRightMaster() {
    return components.getRightMasterMotor();
  }

  public double getYaw() {
    return components.getGyro().getYaw();
  }

  private double cmToEncoderUnits(final double cm) {
    return ENCODER_UNITS * cm / PERIMETER;
  }
}
