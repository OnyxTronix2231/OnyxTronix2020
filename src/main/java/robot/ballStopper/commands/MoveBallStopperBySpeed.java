package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballStopper.Components;
import java.util.function.DoubleSupplier;

public class MoveBallStopperBySpeed extends CommandBase {

  private final Components ballStopper;
  private final DoubleSupplier speedSupplier;

  public MoveBallStopperBySpeed(final Components ballStopper, final DoubleSupplier speedSupplier) {
    this.ballStopper = ballStopper;
    this.speedSupplier = speedSupplier;
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
