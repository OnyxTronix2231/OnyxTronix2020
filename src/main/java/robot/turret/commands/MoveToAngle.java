package robot.turret.commands;

import static robot.turret.TurretConstants.TOLERANCE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngle extends CommandBase {

  private final Turret turret;
  private final DoubleSupplier angle;

  public MoveToAngle(final Turret turret, final DoubleSupplier angle) {
    this.turret = turret;
    this.angle = angle;
    addRequirements(turret);
  }

  @Override
  public void initialize() {
    turret.initEncoders();
  }

  @Override
  public void execute() {
    turret.moveToAngle(angle.getAsDouble());
    System.out.printf("I\'m Working :)\n");
  }

  @Override
  public boolean isFinished() {
    return Math.abs(turret.getEncoderPosition() - turret.convertAngleToEncoderUnits(angle.getAsDouble())) < TOLERANCE;
  }

  @Override
  public void end(boolean interrupted) {
    turret.stopMotor();
  }
}
