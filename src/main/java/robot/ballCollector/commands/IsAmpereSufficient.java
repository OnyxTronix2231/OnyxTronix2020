package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;

public class IsAmpereSufficient extends CommandBase {

  private final BallCollector ballCollector;

  public IsAmpereSufficient(final BallCollector ballCollector) {
    this.ballCollector = ballCollector;
  }

  @Override
  public boolean isFinished() {
    return ballCollector.isBallCollected();
  }
}
