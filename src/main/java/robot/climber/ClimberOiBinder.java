package robot.climber;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.climber.commands.Climb;
import robot.climber.commands.ClimbBySpeed;
import robot.climber.commands.OpenClimberPistons;

public class ClimberOiBinder {

  public ClimberOiBinder(final Climber climber, final Trigger openClimber, final Trigger climb) {
    openClimber.toggleWhenActive(new OpenClimberPistons(climber));

    climb.whileActiveContinuous(new ClimbBySpeed(climber, () -> 0.3));
  }
}
