package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import robot.ballCollector.BallCollector;

import java.util.function.DoubleSupplier;

public class CollectAndCount extends ParallelDeadlineGroup {

  private final BallCollector ballCollector;
  private final DoubleSupplier speedSupplier;

  public CollectAndCount(final BallCollector ballCollector, final DoubleSupplier speedSupplier){
    super(new CollectBallBySpeed(ballCollector, speedSupplier), new CountBall(ballCollector));
    this.ballCollector = ballCollector;
    this.speedSupplier = speedSupplier;
  }
}
