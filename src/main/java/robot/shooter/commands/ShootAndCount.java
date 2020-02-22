package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballCollector.BallCollector;
import robot.crossSubsystem.commands.SpinShooterAndLoaderByDistance;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootAndCount extends ParallelCommandGroup {

  public ShootAndCount(final SpinShooterAndLoaderByDistance spinShooterAndLoaderByDistance,
                       final BallCollector ballCollector, final Shooter shooter){
    super(spinShooterAndLoaderByDistance, new CountShotBall(ballCollector, shooter));
  }
}
