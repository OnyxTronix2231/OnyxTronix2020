package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballStopper.BallStopper;

import java.util.function.DoubleSupplier;

public class MoveBallStopper extends CommandBase {

  private final robot.ballStopper.BallStopper ballStopper;
  private final DoubleSupplier speedSupplier;

  public MoveBallStopper(final BallStopper ballStopper, final DoubleSupplier speedSupplier) {
    this.ballStopper = ballStopper;
    this.speedSupplier = speedSupplier;
  }

  @Override
  public void execute() {
    ballStopper.moveBallStopperMotor(speedSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    ballStopper.stopMotor();
  }
}
