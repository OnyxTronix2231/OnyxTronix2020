package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballStopper.BallStopper;

import java.util.function.DoubleSupplier;

public class MoveRightMotor extends CommandBase {

  private final BallStopper ballStopper;
  private final DoubleSupplier speedSupplier;

  public MoveRightMotor(final BallStopper ballStopper, final DoubleSupplier speedSupplier) {
    this.ballStopper = ballStopper;
    this.speedSupplier = speedSupplier;
    addRequirements(ballStopper);
  }

  @Override
  public void execute() {
    ballStopper.moveRightMotor(speedSupplier.getAsDouble());
  }
}
