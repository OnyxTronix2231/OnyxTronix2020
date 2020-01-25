package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedController;

public class VelocityController implements SpeedController {

  private final double maxVelocity;
  private final int pidSlot;
  private final WPI_TalonFX motor;

  public VelocityController(final double maxVelocity, final int pidSlot, final WPI_TalonFX motor){
    this.maxVelocity = maxVelocity;
    this.pidSlot = pidSlot;
    this.motor = motor;
  }

  public double getVelocityBySpeed(final double speed){
    return maxVelocity * speed;
  }

  @Override
  public void set(final double speed) {
    motor.selectProfileSlot(pidSlot,0);
    if(speed != 0){
      motor.set(ControlMode.Velocity,getVelocityBySpeed(speed));
    }
    else{
      motor.set(ControlMode.PercentOutput,0);
    }
  }

  @Override
  public double get() {
    return motor.get();
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
    motor.disable();
  }

  @Override
  public void stopMotor() {
    motor.set(ControlMode.PercentOutput,0);
  }

  @Override
  public void pidWrite(final double output) {
    motor.pidWrite(output);
  }
}
