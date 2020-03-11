package robot.ballCollector.commands;

import static robot.ballCollector.BallCollectorConstants.CLOSING_SEQUENCE_DELAY;
import static robot.ballCollector.BallCollectorConstants.CLOSING_SEQUENCE_TIMEOUT;
import static robot.ballCollector.BallCollectorConstants.DURING_CLOSED_PERCENT_OUTPUT;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballCollector.BallCollector;

import java.util.function.DoubleSupplier;

public class OpenAndCollect extends SequentialCommandGroup {

  private final BallCollector ballCollector;

  public OpenAndCollect(final BallCollector ballCollector, final DoubleSupplier speedSupplier) {
    super(new OpenBallCollectorPistons(ballCollector), new CollectBallBySpeed(ballCollector, speedSupplier));
    this.ballCollector = ballCollector;
  }
}
