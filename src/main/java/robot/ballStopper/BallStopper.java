package robot.ballStopper;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallStopper extends SubsystemBase {

  private final BallStopperComponents components;

  public BallStopper(final BallStopperComponents ballStopperComponents) {
    this.components = ballStopperComponents;
  }

  public void moveLeftMotor(final double speed) {
    components.getLeftMotor().set(speed);
  }

  public void moveRightMotor(final double speed) {
    components.getRightMotor().set(speed);
  }

  public void stopMotor() {
    moveLeftMotor(0);
    moveRightMotor(0);
  }
}
