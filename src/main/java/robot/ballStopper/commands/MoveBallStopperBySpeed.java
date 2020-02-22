package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import java.util.function.DoubleSupplier;

public class MoveBallStopperBySpeed extends ParallelCommandGroup {

  public MoveBallStopperBySpeed(robot.ballStopper.BallStopper ballStopper, DoubleSupplier ballStopperSpeed, double ballStopperDelay) {
    super(sequence(
        new MoveBallStopper(ballStopper, ballStopperSpeed).withTimeout(ballStopperDelay),
        parallel(
            new MoveBallStopperDelay(ballStopper, ballStopperSpeed),
            new MoveBallStopper(ballStopper, ballStopperSpeed))));
  }
}

