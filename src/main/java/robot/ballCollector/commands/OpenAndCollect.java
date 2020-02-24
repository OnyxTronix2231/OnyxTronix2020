package robot.ballCollector.commands;

import static robot.ballCollector.BallCollectorConstants.BALL_COLLECTOR_RAMP;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballCollector.BallCollector;

import java.util.function.DoubleSupplier;

public class OpenAndCollect extends SequentialCommandGroup {

  public OpenAndCollect(final BallCollector ballCollector,
                        final DoubleSupplier speedSupplier) {
    addCommands(new OpenBallCollectorPistons(ballCollector), new CollectBallBySpeed(ballCollector,
        () -> speedSupplier.getAsDouble() * BALL_COLLECTOR_RAMP));
  }
}
