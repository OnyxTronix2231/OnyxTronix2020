package robot.ballCounter.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.ballCollector.BallCollector;
import robot.ballCollector.commands.WaitUntilBallCollected;
import robot.ballCounter.BallCounter;

public class WaitTillAmpereThenCount extends SequentialCommandGroup {
  public WaitTillAmpereThenCount(final BallCollector ballCollector, final BallCounter ballCounter) {
    addCommands(new WaitUntilBallCollected(ballCollector), new WaitUntilCommand(ballCollector::isBallNotCollected),
        new AddBallToBallCounter(ballCounter));
  }

  @Override
  public boolean isFinished() {
    if (super.isFinished()) {
      initialize();
    }
    return false;
  }
}
