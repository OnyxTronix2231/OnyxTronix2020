package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballStopper.BallStopper;

import java.util.function.DoubleSupplier;

public class MoveBallStopperBySpeed extends ParallelCommandGroup {

  public MoveBallStopperBySpeed(BallStopper ballStopper, DoubleSupplier ballStopperSpeed , double ballStopperDelay){
    super(parallel(
        new MoveLeftMotor(ballStopper, ballStopperSpeed),
        new WaitCommand(ballStopperDelay),
        new MoveRightMotor(ballStopper, ballStopperSpeed)));
  }
}

