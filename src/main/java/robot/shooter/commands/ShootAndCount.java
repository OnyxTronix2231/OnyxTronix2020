package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballCollector.BallCollector;
import robot.ballCounter.BallCounter;
import robot.ballCounter.commands.CountCollectedBall;
import robot.crossSubsystem.commands.SpinShooterAndLoaderByDistance;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.vision.Vision;

import java.util.function.DoubleSupplier;

public class ShootAndCount extends ParallelCommandGroup {

  public ShootAndCount(final BallCollector ballCollector, final Shooter shooter, final BallCounter ballCounter,
                       final LoaderConveyor loaderConveyor, final Vision vision){
    super(new SpinShooterAndLoaderByDistance(shooter, loaderConveyor, () -> vision.getOuterTarget().getDistance()),
        new CountCollectedBall(ballCounter, ballCounter::canShoot, false));
  }
}
