package robot.ballStopper;

public class Components {

  private final BasicBallStopperComponents Components;

  public Components(final BasicBallStopperComponents ballStopperComponents) {
    this.Components = ballStopperComponents;
  }

  public void moveBallStopperBySpeed(final double speed) {
    Components.getMotor().set(speed);
  }

  public void stopMotor() {
    moveBallStopperBySpeed(0);
  }
}

