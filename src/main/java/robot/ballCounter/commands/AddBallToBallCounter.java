package robot.ballCounter.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCounter.BallCounter;

public class AddBallToBallCounter extends InstantCommand {

  private final BallCounter ballCounter;
  public AddBallToBallCounter(final BallCounter ballCounter) {
    this.ballCounter = ballCounter;
  }

  @Override
  public void initialize() {
    ballCounter.ballCollected();
  }
}
