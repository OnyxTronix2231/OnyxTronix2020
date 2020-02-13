package robot.climber;

import static robot.climber.ClimberConstants.CLOSE_SOLENOID_VALUE;
import static robot.climber.ClimberConstants.OPEN_SOLENOID_VALUE;

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
    components.getDoubleSolenoid().set(CLOSE_SOLENOID_VALUE);
  }

  public void openPistons() {
    components.getDoubleSolenoid().set(OPEN_SOLENOID_VALUE);
  }
}
