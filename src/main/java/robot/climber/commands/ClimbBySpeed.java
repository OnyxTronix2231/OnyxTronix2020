package robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.climber.Climber;

import java.util.function.DoubleSupplier;

public class ClimbBySpeed extends CommandBase {

  private final Climber climber;
  private final DoubleSupplier speedSupplier;

  public ClimbBySpeed(final Climber climber, final DoubleSupplier speedSupplier) {
    this.climber = climber;
    this.speedSupplier = speedSupplier;
    addRequirements(climber);
  }

  @Override
  public void execute() {
    climber.climbBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    climber.climbBySpeed(0);
  }
}
