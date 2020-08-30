package robot.climber;

import static robot.climber.ClimberConstants.UP_SPEED;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.climber.commands.Climb;
import robot.climber.commands.ClimbBySpeed;
import robot.climber.commands.OpenClimberPistons;

public class ClimberOiBinder {

  public ClimberOiBinder(final Climber climber, final Trigger openClimber, final Trigger climb, final Trigger rollUp, final Trigger rollDown) {
    openClimber.toggleWhenActive(new OpenClimberPistons(climber));

    climb.whileActiveContinuous(new Climb(climber));

    rollUp.whileActiveContinuous(new ClimbBySpeed(climber, () -> UP_SPEED));
    rollDown.whileActiveContinuous(new ClimbBySpeed(climber, () -> -UP_SPEED));
  }
}
