package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;
import robot.ballCounter.BallCounter;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.vision.Vision;

public class ShootCondition extends ConditionalCommand {

  public ShootCondition(final BallCounter ballCounter, final Shooter shooter, final BallCollector ballCollector,
                        final LoaderConveyor loaderConveyor, final Vision vision){
    super(new IsReadyToShoot(shooter, ballCollector, ballCounter, loaderConveyor, vision), new InstantCommand(), ballCounter::canShoot);
  }
}
