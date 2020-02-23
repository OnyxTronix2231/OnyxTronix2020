package robot.ballCollector.commands;

import static robot.ballCollector.BallCollectorConstants.PERCENT_OUTPUT;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballCollector.BallCollector;

public class OpenAndCollect extends SequentialCommandGroup {

  public OpenAndCollect(final BallCollector ballCollector) {
    addCommands(new OpenBallCollectorPistons(ballCollector), new CollectBallBySpeed(ballCollector, () -> PERCENT_OUTPUT));
  }
}
