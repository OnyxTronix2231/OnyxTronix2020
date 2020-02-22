package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;

public class StopCollectingCondition extends ConditionalCommand {

  public StopCollectingCondition(final CountBall countBall, final BallCollector ballCollector) {
    super(countBall, new InstantCommand(), ballCollector::canCollect);
  }
}
