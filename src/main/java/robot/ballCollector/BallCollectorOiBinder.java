package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.CLOSING_SEQUENCE_DELAY;
import static robot.ballCollector.BallCollectorConstants.CLOSING_SEQUENCE_TIMEOUT;
import static robot.ballCollector.BallCollectorConstants.DURING_CLOSED_PERCENT_OUTPUT;
import static robot.ballCollector.BallCollectorConstants.PERCENT_OUTPUT;
import static robot.ballCollector.BallCollectorConstants.REVERSE_OUTPUT;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenAndCollect;
import robot.ballCollector.commands.OpenBallCollectorPistons;

public class BallCollectorOiBinder {

  public BallCollectorOiBinder(final BallCollector ballCollector, final Trigger openAndCollect,
                               final Trigger uncollectBalls, final Trigger openThenCloseCollector) {

    openAndCollect.whileActiveContinuous(new OpenAndCollect(new OpenBallCollectorPistons(ballCollector),
        new CollectBallBySpeed(ballCollector, () -> PERCENT_OUTPUT)));

    openAndCollect.whenInactive(new CloseBallCollectorPistons(ballCollector).
        andThen(new WaitCommand(CLOSING_SEQUENCE_DELAY))
        .andThen(new CollectBallBySpeed(ballCollector,
            () -> DURING_CLOSED_PERCENT_OUTPUT).withTimeout(CLOSING_SEQUENCE_TIMEOUT)));

    uncollectBalls.whileActiveContinuous(new CollectBallBySpeed(ballCollector, () -> REVERSE_OUTPUT))
        .whenActive(new OpenBallCollectorPistons(ballCollector))
        .whenInactive(new CloseBallCollectorPistons(ballCollector));

    openThenCloseCollector.whenActive(new OpenBallCollectorPistons(ballCollector))
        .whenInactive(new CloseBallCollectorPistons(ballCollector));
  }
}
