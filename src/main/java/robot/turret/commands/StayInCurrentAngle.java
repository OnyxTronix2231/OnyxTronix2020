package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

public class StayInCurrentAngle extends CommandBase {

  private double firstPosition;
  private final Turret turret;

  public StayInCurrentAngle(final Turret turret){
    this.turret = turret;
    addRequirements(turret);
  }

  @Override
  public void initialize() {
    firstPosition = turret.getAngle();
  }

  @Override
  public void execute() {
    turret.moveToAngle(firstPosition);
  }
}
