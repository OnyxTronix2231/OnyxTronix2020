package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballStopper.BallStopper;

import java.util.function.DoubleSupplier;

public class MoveBallStopperBySpeed extends SequentialCommandGroup {

  public MoveBallStopperBySpeed(BallStopper ballStopper, DoubleSupplier ballStopperSpeed, double ballStopperDelay) {
    super(
        new MoveBallStopper(ballStopper, ballStopperSpeed).withTimeout(ballStopperDelay),
        parallel(
            new MoveBallStopperDelay(ballStopper, ballStopperSpeed),
            new MoveBallStopper(ballStopper, ballStopperSpeed)));
  }
}
