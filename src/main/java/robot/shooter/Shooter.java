package robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;

  public Shooter(final ShooterComponents components) {
    this.components = components;
  }

  public final void shootBySpeed(final DoubleSupplier speedSupplier) {
    components.getMasterMotor().set(speedSupplier.getAsDouble());
  }

  public final void stopMotor() {
    shootBySpeed(() -> 0);
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }
}
