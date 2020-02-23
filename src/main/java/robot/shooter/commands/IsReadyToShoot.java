package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;
import robot.ballCounter.BallCounter;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.vision.Vision;

public class IsReadyToShoot extends ConditionalCommand {

  public IsReadyToShoot(final Shooter shooter, final BallCollector ballCollector, final BallCounter ballCounter,
                        final LoaderConveyor loaderConveyor, final Vision vision){
    super(new ShootAndCount(ballCollector, shooter, ballCounter, loaderConveyor, vision),
        new InstantCommand(), shooter::isReadyToShoot);
  }
}
