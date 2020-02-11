package robot.ballStopper;


public class BallStopper {

  private final BasicBallStopperComponents ballStopperComponents;

  public BallStopper(final BasicBallStopperComponents ballStopperComponents) {
    this.ballStopperComponents = ballStopperComponents;
  }

  public void moveBallStopperBySpeed(final double speed) {
    ballStopperComponents.getMotor().set(speed);
  }

  public void stopMotor() {
    moveBallStopperBySpeed(0);
  }
}

