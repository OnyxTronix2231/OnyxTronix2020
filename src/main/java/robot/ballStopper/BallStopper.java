package robot.ballStopper;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallStopper extends SubsystemBase {

  private final BallStopperComponents components;

  public BallStopper(final BallStopperComponents ballStopperComponents) {
    this.components = ballStopperComponents;
  }

  public void moveBallStopperBySpeed(final double speed) {
    components.getMotor().set(speed);
  }

  public void stopMotor() {
    moveBallStopperBySpeed(0);
  }
}
