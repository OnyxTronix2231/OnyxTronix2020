package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;
import robot.ballCollector.commands.CountBall;

public class StopShootingCondition extends ConditionalCommand {

  public StopShootingCondition(final CountShotBall countShotBall, final BallCollector ballCollector){
    super(countShotBall, new InstantCommand(), ballCollector::canShoot);
  }
}
