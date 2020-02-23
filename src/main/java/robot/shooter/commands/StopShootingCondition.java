package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;
import robot.ballCounter.BallCounter;

public class StopShootingCondition extends ConditionalCommand {

  public StopShootingCondition(final CountShotBall countShotBall, final BallCounter ballCounter){
    super(countShotBall, new InstantCommand(), ballCounter::canShoot);
  }
}
