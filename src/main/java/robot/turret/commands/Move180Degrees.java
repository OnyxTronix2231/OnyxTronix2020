package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

public class Move180Degrees extends CommandBase {

  private final double angle = 180;
  private final Turret turret;

  public Move180Degrees(final Turret turret){
    this.turret = turret;
    addRequirements(turret);
  }

  @Override
  public void execute() {
    turret.moveToAngle(turret.getAngle() + angle);
  }
}
