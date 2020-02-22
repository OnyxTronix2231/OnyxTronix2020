package robot.basicautonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.drivetrain.commands.DriveByDistance;
import robot.shooter.commands.ShootAndCount;
import robot.turret.commands.MoveTurretByAngle;

public class AutonomousShootCommand extends ParallelCommandGroup {
  public AutonomousShootCommand(final MoveTurretByAngle moveTurretByAngle, final ShootAndCount shootAndCount,
                                final DriveByDistance driveByDistance) {
    super(
        moveTurretByAngle,
        shootAndCount,
        // if count == 0 stop
        driveByDistance
    );
  }
}
