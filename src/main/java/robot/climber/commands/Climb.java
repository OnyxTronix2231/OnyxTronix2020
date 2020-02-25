package robot.climber.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.climber.Climber;
import robot.climber.ClimberConstants;

public class Climb extends SequentialCommandGroup {

  public Climb(final Climber climber) {
    super(new CloseClimberPistons(climber),
        new WaitCommand(0.5), new ClimbByDistance(climber, () -> ClimberConstants.SET_POINT_ON_BAR));
  }
}
