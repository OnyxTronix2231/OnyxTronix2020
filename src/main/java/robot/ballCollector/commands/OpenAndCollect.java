package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class OpenAndCollect extends SequentialCommandGroup {

  public OpenAndCollect(final OpenBallCollectorPistons openBallCollectorPistons,
                        final CollectBallBySpeed collectBallBySpeed) {
    addCommands(openBallCollectorPistons, collectBallBySpeed);
  }
}
