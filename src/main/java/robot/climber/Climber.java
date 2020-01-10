package robot.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  private final ClimberComponents components;

  public Climber(final ClimberComponents components) {
    this.components = components;
  }

  public final void moveBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotor() {
    components.getMasterMotor().set(0);
  }

  public final void closeDoubleSolenoid() {
    components.getDoubleSolenoid().set(DoubleSolenoid.Value.kReverse);
  }

  public final void openDoubleSolenoid() {
    components.getDoubleSolenoid().set(DoubleSolenoid.Value.kForward);
  }
}
