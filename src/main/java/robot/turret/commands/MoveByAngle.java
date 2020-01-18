package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveByAngle extends CommandBase {

  private final Turret turret;
  private final DoubleSupplier angleSupplier;

  public MoveByAngle(Turret turret, DoubleSupplier angleSupplier) {
    this.turret = turret;
    this.angleSupplier = angleSupplier;
    addRequirements(turret);
  }

  @Override
  public void execute() {
    turret.moveByAngle(angleSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    turret.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return turret.isOnTarget();
  }
}
