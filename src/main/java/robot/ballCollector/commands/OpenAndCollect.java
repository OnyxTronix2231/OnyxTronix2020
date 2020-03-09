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
    this.ballCollector = ballCollector;
    addCommands(new OpenBallCollectorPistons(ballCollector), new CollectBallBySpeed(ballCollector, speedSupplier));
  }

  @Override
  public void end(final boolean interrupted) {
    new CloseBallCollectorPistons(ballCollector).alongWith(sequence(new WaitCommand(CLOSING_SEQUENCE_DELAY),
    new CollectBallBySpeed(ballCollector, () -> DURING_CLOSED_PERCENT_OUTPUT).
        withTimeout(CLOSING_SEQUENCE_TIMEOUT))).schedule();

  }
}
