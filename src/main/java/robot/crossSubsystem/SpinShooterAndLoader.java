package robot.crossSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.LoaderConveyor.LoaderConveyor;
import robot.LoaderConveyor.commands.MoveLoaderByVelocity;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocity;

import java.util.function.DoubleSupplier;

public class SpinShooterAndLoader extends ParallelCommandGroup {
  public SpinShooterAndLoader(Shooter shooter, LoaderConveyor loaderConveyor, DoubleSupplier velocitySupplier){

    super(new ShootByVelocity(shooter, velocitySupplier),
          new MoveLoaderByVelocity(loaderConveyor, velocitySupplier));
  }
}
