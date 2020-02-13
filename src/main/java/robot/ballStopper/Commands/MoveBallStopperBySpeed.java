package robot.ballStopper.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballStopper.BallStopper;
import java.util.function.DoubleSupplier;

public class MoveBallStopperBySpeed extends CommandBase {

  private final BallStopper BallStopper;
  private final DoubleSupplier speedSupplier;

  public MoveBallStopperBySpeed(final BallStopper ballStopper, final DoubleSupplier speedSupplier) {
    this.BallStopper = ballStopper;
    this.speedSupplier = speedSupplier;
  }

  @Override
  public void execute() {
    BallStopper.moveBallStopperBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    BallStopper.stopMotor();
  }

}
