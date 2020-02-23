package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballCollector.BallCollector;
import robot.ballCounter.BallCounter;
import robot.ballCounter.commands.RemoveBallFromBallCounter;
import robot.shooter.Shooter;

public class CountShotBall extends SequentialCommandGroup {

  public CountShotBall(final BallCollector ballCollector, final Shooter shooter, final BallCounter ballCounter) {
    addCommands(new WaitTillRightAmp(shooter), new RemoveBallFromBallCounter(ballCounter),
        new StopShootingCondition(this, ballCounter));
  }
}
