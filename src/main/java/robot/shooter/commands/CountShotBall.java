package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballCollector.BallCollector;
import robot.ballCollector.commands.RemoveBallFromCounter;
import robot.shooter.Shooter;

public class CountShotBall extends SequentialCommandGroup {

  private final BallCollector ballCollector;
  private final Shooter shooter;

  public CountShotBall(final BallCollector ballCollector, final Shooter shooter) {
    this.ballCollector = ballCollector;
    this.shooter = shooter;
    addCommands(new WaitTillRightAmp(shooter), new RemoveBallFromCounter(ballCollector),
        new StopShootingCondition(this, ballCollector));
  }
}
