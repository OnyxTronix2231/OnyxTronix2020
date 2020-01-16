package robot.BallStopper;


public class BallStopper {

  private final BasicBallStopperComponents ballStopperComponents;

  public BallStopper(final BasicBallStopperComponents conveyorOneBallComponents) {
    this.ballStopperComponents = conveyorOneBallComponents;
  }

  public final void moveBallStopperBySpeed(final double speed) {
    ballStopperComponents.getMasterMotor().set(speed);
  }

  public final void stopMotorBallStopper() {
    ballStopperComponents.getMasterMotor().set(0);
  }
}

