package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;
import robot.yawControl.YawControl;

import java.util.function.DoubleSupplier;

public class MoveTurretToAngleAndKeep extends CommandBase {

  protected final Turret turret;
  protected final DoubleSupplier supplierAngle;
  protected double angle;

  public MoveTurretToAngleAndKeep(final Turret turret, final DoubleSupplier angleSupplier) {
    this.turret = turret;
    this.supplierAngle = angleSupplier;
    addRequirements(turret);
  }

  @Override
  public void initialize() {
    angle = supplierAngle.getAsDouble();

    turret.initMoveMotionMagic();
  }

  @Override
  public void execute() {
    turret.moveToAngle(angle);
  }

  @Override
  public void end(final boolean interrupted) {
    turret.stopMotor();
  }
}
