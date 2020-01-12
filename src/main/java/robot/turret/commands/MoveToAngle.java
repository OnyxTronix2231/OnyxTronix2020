package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngle extends InstantCommand {

  private final Turret turret;
  private final double angle;

  public MoveToAngle(final Turret turret, final double angle) {
    this.turret = turret;
    this.angle = angle;
    addRequirements(turret);
  }

  @Override
  public void initialize() {
    turret.moveToAngle(angle);
  }

}
