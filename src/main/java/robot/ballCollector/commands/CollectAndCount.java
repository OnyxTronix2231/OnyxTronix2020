package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import robot.ballCollector.BallCollector;
import robot.ballCounter.BallCounter;
import robot.ballCounter.commands.CountBall;

public class CollectAndCount extends ParallelDeadlineGroup {

  public CollectAndCount(final BallCounter ballCounter, final BallCollector ballCollector){
    super(new CountBall(ballCounter, ballCollector::isBallCollected, true),
        new OpenAndCollect(ballCollector));
  }
}
