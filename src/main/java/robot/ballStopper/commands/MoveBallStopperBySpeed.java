package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.function.DoubleSupplier;

public class MoveBallStopperBySpeed extends SequentialCommandGroup {

  public MoveBallStopperBySpeed(robot.ballStopper.BallStopper ballStopper, DoubleSupplier ballStopperSpeed, double ballStopperDelay) {
    super(
        new MoveBallStopper(ballStopper, ballStopperSpeed).withTimeout(ballStopperDelay),
        parallel(
            new MoveBallStopperDelay(ballStopper, ballStopperSpeed),
            new MoveBallStopper(ballStopper, ballStopperSpeed)));
  }
}
