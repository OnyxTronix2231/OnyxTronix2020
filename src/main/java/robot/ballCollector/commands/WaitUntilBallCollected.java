package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;

public class WaitUntilBallCollected extends CommandBase {

  private final BallCollector ballCollector;

  public WaitUntilBallCollected(final BallCollector ballCollector) {
    this.ballCollector = ballCollector;
  }

  @Override
  public void initialize() {
    ballCollector.startChecking();
  }

  @Override
  public boolean isFinished() {
    return ballCollector.isBallCollected();
  }
}
