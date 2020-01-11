package robot.shooter;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;

  public Shooter(final ShooterComponents components) {
    this.components = components;
  }

  public final void shootBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotor() {
    shootBySpeed(0);
  }
}
