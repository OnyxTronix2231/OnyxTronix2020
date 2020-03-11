package robot.climber;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.climber.commands.Climb;
import robot.climber.commands.OpenClimberPistons;

public class ClimberOiBinder {

  public ClimberOiBinder(final Climber climber, final Trigger openClimber, final Trigger climb) {
    openClimber.whenActive(new OpenClimberPistons(climber));

    climb.whenActive(new Climb(climber));
  }
}
