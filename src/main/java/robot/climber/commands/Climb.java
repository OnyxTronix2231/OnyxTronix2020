package robot.climber.commands;

import static robot.climber.ClimberConstants.UP_SPEED;
import static robot.climber.ClimberConstants.ClimberComponentsA.WAIT_UNTIL_CLIMB;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.climber.Climber;

public class Climb extends SequentialCommandGroup {

  public Climb(final Climber climber) {
    super(new CloseClimberPistons(climber),
        new WaitCommand(WAIT_UNTIL_CLIMB), new ClimbBySpeed(climber, () -> UP_SPEED));
  }
}
