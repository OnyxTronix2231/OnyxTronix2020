package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballStopper.BallStopper;

import java.util.function.DoubleSupplier;

public class MoveBallStopperDelay extends CommandBase {

  private final BallStopper ballStopper;
  private final DoubleSupplier speedSupplier;

  public MoveBallStopperDelay(final BallStopper ballStopper, final DoubleSupplier speedSupplier) {
    this.ballStopper = ballStopper;
    this.speedSupplier = speedSupplier;
  }

  @Override
  public void execute() {
    ballStopper.moveBallStopperDelayMotor(speedSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    ballStopper.stopMotor();
  }
}
