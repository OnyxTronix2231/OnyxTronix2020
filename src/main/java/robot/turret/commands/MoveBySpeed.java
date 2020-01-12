package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveBySpeed extends CommandBase {

  private final Turret turret;
  private final DoubleSupplier speedSupplier;

  public MoveBySpeed(final Turret turret, final DoubleSupplier speedSupplier) {
    this.turret = turret;
    this.speedSupplier = speedSupplier;
    addRequirements(turret);
  }

  @Override
  public void execute() {
    turret.moveBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    turret.stopMotor();
  }
}
