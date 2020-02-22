package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballCollector.BallCollector;
import robot.shooter.commands.StopShootingCondition;

public class CountBall extends SequentialCommandGroup {

  private final BallCollector ballCollector;

  public CountBall(final BallCollector ballCollector){
    this.ballCollector = ballCollector;
    addCommands(new WaitTillRightAmp(ballCollector), new AddBallToCounter(ballCollector),
        new StopCollectingCondition(this, ballCollector));
  }
}
