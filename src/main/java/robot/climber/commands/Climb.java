package robot.climber.commands;

import static robot.climber.ClimberConstants.*;
import static robot.climber.ClimberConstants.ClimberComponentsA.WAIT_UNTIL_CLIMB;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.climber.Climber;
import robot.climber.ClimberConstants;

public class Climb extends SequentialCommandGroup {

  public Climb(final Climber climber) {
    super(new CloseClimberPistons(climber),
        new WaitCommand(WAIT_UNTIL_CLIMB), new ClimbBySpeed(climber, () -> 1));
  }
}
