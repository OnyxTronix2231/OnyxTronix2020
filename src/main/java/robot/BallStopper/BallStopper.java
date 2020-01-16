package robot.BallStopper;


public class BallStopper {

  private final BasicBallStopperComponents ballStopperComponents;

  public BallStopper(final BasicBallStopperComponents ballStopperComponents) {
    this.ballStopperComponents = ballStopperComponents;
  }

  public final void moveBallStopperBySpeed(final double speed) {
    ballStopperComponents.getMasterMotor().set(speed);
  }

  public final void stopMotor() {
    ballStopperComponents.getMasterMotor().set(0);
  }
}

