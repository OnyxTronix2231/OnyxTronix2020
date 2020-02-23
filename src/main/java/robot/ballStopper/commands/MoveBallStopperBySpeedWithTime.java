package robot.ballStopper.commands;

import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.TIME_TO_MOVE_BALL_STOPPER;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballStopper.BallStopper;

import java.util.function.DoubleSupplier;

public class MoveBallStopperBySpeedWithTime extends WaitCommand {

  private final BallStopper ballStopper;
  private final DoubleSupplier speedSupplier;

  public MoveBallStopperBySpeedWithTime(final BallStopper ballStopper, final DoubleSupplier speedSupplier,
                                        final double timeToMove) {
    super(timeToMove);
    this.ballStopper = ballStopper;
    this.speedSupplier = speedSupplier;
    addRequirements(ballStopper);
  }

  @Override
  public void execute() {
    ballStopper.moveBallStopperBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    ballStopper.stopMotor();
  }
}
