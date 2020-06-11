package robot.ballCollector.commands;

import static robot.ballCollector.BallCollectorConstants.*;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballCollector.BallCollector;

public class ClosePistonsWhileMovingBallCollector extends SequentialCommandGroup {
  public ClosePistonsWhileMovingBallCollector(BallCollector ballCollector) {
    super(new CloseBallCollectorPistons(ballCollector), new WaitCommand(CLOSING_SEQUENCE_DELAY),
        new CollectBallBySpeed(ballCollector, () -> DURING_CLOSED_PERCENT_OUTPUT).
            withTimeout(CLOSING_SEQUENCE_TIMEOUT));
  }
}


