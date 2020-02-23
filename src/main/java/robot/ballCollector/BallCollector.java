package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.MIN_AMP_FOR_ONE;
import static robot.ballCollector.BallCollectorConstants.CLOSE_SOLENOID;
import static robot.ballCollector.BallCollectorConstants.OPEN_SOLENOID;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
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
    components.getSolenoid().set(OPEN_SOLENOID);
  }

  public void closePistons() {
    components.getSolenoid().set(CLOSE_SOLENOID);
  }

  public boolean isBallCollected() {
    return getAmp() > MIN_AMP_FOR_ONE && getAmp() < 30;
  }

  public double getAmp() {
    return components.getMasterMotor().getStatorCurrent();
  }
}
