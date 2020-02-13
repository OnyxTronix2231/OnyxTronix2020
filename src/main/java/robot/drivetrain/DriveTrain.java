package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.ARB_FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_FORWARD_SENSITIVITY;
import static robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_ROTATION_SENSITIVITY;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT;
import static robot.drivetrain.DriveTrainConstants.ENCODER_UNITS;
import static robot.drivetrain.DriveTrainConstants.PERIMETER;
import static robot.drivetrain.DriveTrainConstants.PRIMARY_PID;
import static robot.drivetrain.DriveTrainConstants.TOLERANCE;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  private final BasicDriveTrainComponentsA components;

  public DriveTrain(final BasicDriveTrainComponentsA components) {
    this.components = components;
  }

  public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
    components.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
        rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
  }

  public void driveByMotionMagic(final double distance) {
    moveMotorByMotionMagic(getLeftMaster(), distance);
    moveMotorByMotionMagic(getLeftMaster(), distance);
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

  public void initMotionProfileSlot() {
    selectProfileSlot(getLeftMaster());
    selectProfileSlot(getRightMaster());
  }

  private void moveMotorByMotionMagic(final TalonFX motor, final double distance) {
    motor.set(ControlMode.MotionMagic, cmToEncoderUnits(distance) + motor.getSelectedSensorPosition(),
        DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
  }

  private void selectProfileSlot(final TalonFX motor) {
    motor.selectProfileSlot(DRIVE_BY_DISTANCE_SLOT, PRIMARY_PID);
  }

  private TalonFX getLeftMaster() {
    return components.getLeftMasterMotor();
  }

  private TalonFX getRightMaster() {
    return components.getRightMasterMotor();
  }

  private double cmToEncoderUnits(final double cm) {
    return ENCODER_UNITS * cm / PERIMETER;
  }

  public void stopDrive() {
    components.getDifferentialDrive().stopMotor();
  }
}
