package robot.ballStopper;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallStopper extends SubsystemBase {

  private final BallStopperComponents components;

  public BallStopper(final BallStopperComponents ballStopperComponents) {
    this.components = ballStopperComponents;
  }

  public void moveBallStopperMotor(final double speed) {
    components.getBallStopperMotor().set(speed);
  }

  public void moveBallStopperDelayMotor(final double speed) {
    components.getDelayMotor().set(speed);
  }

}
