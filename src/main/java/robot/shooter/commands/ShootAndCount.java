package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballCollector.BallCollector;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootAndCount extends ParallelCommandGroup {

  private final Shooter shooter;
  private final DoubleSupplier velocitySupplier;
  private final BallCollector ballCollector;

  public ShootAndCount(final Shooter shooter, final DoubleSupplier velocitySupplier, final BallCollector ballCollector){
    this.shooter = shooter;
    this.velocitySupplier = velocitySupplier;
    this.ballCollector = ballCollector;
    addCommands(new ShootByVelocity(shooter, velocitySupplier), new CountShotBall(ballCollector, shooter));
  }
}
