package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import robot.ballCollector.BallCollector;
import robot.ballCounter.BallCounter;
import robot.ballCounter.commands.CountCollectedBall;

public class CollectAndCount extends ParallelDeadlineGroup {

  public CollectAndCount(final BallCounter ballCounter, final BallCollector ballCollector){
    super(new CountCollectedBall(ballCounter, ballCollector::isBallCollected, true),
        new OpenAndCollect(ballCollector));
  }
}