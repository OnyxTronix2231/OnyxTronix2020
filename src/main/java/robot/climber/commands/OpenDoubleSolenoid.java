package robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.climber.Climber;

public class OpenDoubleSolenoid extends CommandBase {

  private final Climber climber;

  public OpenDoubleSolenoid(final Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.openDoubleSolenoid();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
