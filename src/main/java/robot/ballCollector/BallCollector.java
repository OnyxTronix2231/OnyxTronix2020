package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.MIN_AMP_FOR_ONE;
import static robot.ballCollector.BallCollectorConstants.CLOSE_SOLENOID;
import static robot.ballCollector.BallCollectorConstants.OPEN_SOLENOID;

import com.ctre.phoenix.motorcontrol.IMotorController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallCollector extends SubsystemBase {

  private final BallCollectorComponents components;
  private double lastAmpere;

  public BallCollector(final BallCollectorComponents components) {
    this.components = components;
    lastAmpere = 0;
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

  public void startChecking() {
    lastAmpere = 0;
  }

  public boolean isBallCollected() {
    if (getAmp() > MIN_AMP_FOR_ONE && getAmp() > lastAmpere){
      lastAmpere = getAmp();
      return true;
    }
    return false;
  }

  public boolean isBallNotCollected() {
    return !isBallCollected();
  }

  public double getAmp() {
    return components.getMasterMotor().getStatorCurrent();
  }

  public IMotorController getMasterMotor() {
    return components.getMasterMotor();
  }
}
