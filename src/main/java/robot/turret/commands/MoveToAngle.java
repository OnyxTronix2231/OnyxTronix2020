package robot.turret.commands;

import static robot.turret.TurretConstants.TOLERANCE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

public class MoveToAngle extends CommandBase {

  private final Turret turret;
  private final double angle;

  public MoveToAngle(final Turret turret, final double angle) {
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
    turret.moveToAngle(angle);
    System.out.printf("I\'m Working :)\n");
  }

  @Override
  public boolean isFinished() {
    return Math.abs(turret.getEncoderPosition() - turret.convertAngleToEncoderUnits(angle)) < TOLERANCE;
  }
}
