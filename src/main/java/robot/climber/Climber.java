package robot.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  private final ClimberComponents components;

  public Climber(final ClimberComponents components) {
    this.components = components;
  }

  public void climbBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    climbBySpeed(0);
  }

  public void closePistons() {
    components.getDoubleSolenoid().set(DoubleSolenoid.Value.kReverse);
  }

  public void openPistons() {
    components.getDoubleSolenoid().set(DoubleSolenoid.Value.kForward);
  }
}
