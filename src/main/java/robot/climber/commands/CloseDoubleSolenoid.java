package robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.climber.Climber;

public class CloseDoubleSolenoid extends CommandBase {

  private final Climber climber;

  public CloseDoubleSolenoid(Climber climber) {
    this.climber = climber;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.closeDoubleSolenoid();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
