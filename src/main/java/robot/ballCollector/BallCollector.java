package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.CLOSE_SOLENOID_VALUE;
import static robot.ballCollector.BallCollectorConstants.OPEN_SOLENOID_VALUE;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallCollector extends SubsystemBase {

  private final BallCollectorComponents components;

  public BallCollector(final BallCollectorComponents components) {
    this.components = components;
  }

  public void collectBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    collectBySpeed(0);
  }

  public void openPistons() {
    components.getDoubleSolenoid().set(OPEN_SOLENOID_VALUE);
  }

  public void closePistons() {
    components.getDoubleSolenoid().set(CLOSE_SOLENOID_VALUE);
  }
}
