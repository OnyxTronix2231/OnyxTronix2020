package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import edu.wpi.first.wpilibj.SpeedController;

public abstract class VelocityController implements SpeedController {

  private final int pidSlot;
  private final double maxVelocity;
  private final IMotorController motor;

  public VelocityController(final int pidSlot, final double maxVelocity, final IMotorController motor) {
    this.pidSlot = pidSlot;
    this.maxVelocity = maxVelocity;
    this.motor = motor;
  }

  public void initVelocityController() {
    motor.selectProfileSlot(pidSlot, 0);
  }

  public double getVelocityBySpeed(final double speed) {
    return maxVelocity * speed;
  }

  @Override
  public void set(final double speed) {
    motor.set(ControlMode.Velocity, getVelocityBySpeed(speed));
  }

  @Override
  public void setInverted(final boolean isInverted) {
    motor.setInverted(isInverted);
  }

  @Override
  public boolean getInverted() {
    return motor.getInverted();
  }

  @Override
  public void disable() {
    motor.set(ControlMode.Disabled, 0);
    motor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void stopMotor() {
    motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void pidWrite(final double output) {
    motor.set(ControlMode.PercentOutput, 0);
  }
}
