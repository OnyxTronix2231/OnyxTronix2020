package robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;

  public Shooter(final ShooterComponents components) {
    this.components = components;
    CommandScheduler.getInstance().registerSubsystem(this);
  }

  public final void shootBySpeed(final DoubleSupplier speedSupplier) {
    components.getMasterMotor().set(speedSupplier.getAsDouble());
  }

  public final int getWheelPosition() {
    return components.getMasterMotor().getSelectedSensorPosition(0);
  }

  public final void stopMotor() {
    components.getMasterMotor().set(0);
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }
}
