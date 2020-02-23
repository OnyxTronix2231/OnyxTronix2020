package robot.climber.commands;

import static robot.climber.ClimberConstants.UP_SPEED;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.climber.Climber;
import robot.climber.ClimberConstants;

public class Climb extends SequentialCommandGroup {

  public Climb(Climber climber) {
    super(new CloseClimberPistons(climber), new WaitCommand(0.5), new ClimbBySpeed(climber, () -> UP_SPEED));
  }

}
