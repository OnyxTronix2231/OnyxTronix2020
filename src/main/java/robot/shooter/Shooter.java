package robot.shooter;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;

  public Shooter(final ShooterComponents components) {
    this.components = components;
    CommandScheduler.getInstance().registerSubsystem(this);
  }

  public void shootBySpeed(final DoubleSupplier speedSupplier) {
    components.getMasterMotor().set(speedSupplier.getAsDouble());
  }

  public int getEncoder() {
    return components.getMasterMotor().getSelectedSensorPosition(0);
  }

  public void stopMotor() {
    components.getMasterMotor().set(0);
  }
}
