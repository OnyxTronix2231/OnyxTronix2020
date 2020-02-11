package robot.ballStopper;


public class BallStopper {

  private final BasicBallStopperComponents ballStopperComponents;

  public BallStopper(final BasicBallStopperComponents ballStopperComponents) {
    this.ballStopperComponents = ballStopperComponents;
  }

  public final void moveBallStopperBySpeed(final double speed) {
    ballStopperComponents.getMotor().set(speed);
  }

  public final void stopMotor() {
    ballStopperComponents.getMotor().set(0);
  }
}

