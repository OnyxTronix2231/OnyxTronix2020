package robot.climber.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.climber.Climber;

public class ClosePistons extends InstantCommand {

  private final Climber climber;

  public ClosePistons(final Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.closePistons();
  }

}
