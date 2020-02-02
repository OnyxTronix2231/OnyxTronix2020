package robot.shooter.commands;

import static robot.shooter.ShooterConstants.MAX_FIRST_RANGE;
import static robot.shooter.ShooterConstants.MIN_THIRD_RANGE;
import static robot.shooter.ShooterConstants.SPEED_FIRST;
import static robot.shooter.ShooterConstants.SPEED_MIDDLE;
import static robot.shooter.ShooterConstants.SPEED_THIRD;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

public class ShootByDistance extends CommandBase {

  private final double distance;
  private final Shooter shooter;

  ShootByDistance(double distance, Shooter shooter) {
    this.distance = distance;
    this.shooter = shooter;
  }

  @Override
  public void execute() {
    if (distance > MIN_THIRD_RANGE) {
      shooter.setVelocity(SPEED_THIRD);
    } else if (distance > MAX_FIRST_RANGE && distance < MIN_THIRD_RANGE) {
      shooter.setVelocity(SPEED_MIDDLE);
    } else {
      shooter.setVelocity(SPEED_FIRST);
    }
  }

  @Override
  public void end(final boolean interrupted) {
    shooter.stopMotor();
  }
}
