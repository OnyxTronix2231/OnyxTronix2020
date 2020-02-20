package robot.autonomous;

import static robot.drivetrain.DriveTrainConstants.Paths.PATHS;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballCollector.BallCollector;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.OnyxTrajectoryGenerator;
import robot.drivetrain.commands.MoveByPath;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootBySpeed;

public class SecondaryPath extends SequentialCommandGroup {
  public SecondaryPath(final DriveTrain driveTrain, final OnyxTrajectoryGenerator trajectoryGenerator,
                       final BallCollector ballCollector, final Shooter shooter) {
    addCommands(
        new ParallelDeadlineGroup(new MoveByPath(driveTrain, trajectoryGenerator, PATHS[0][0]),
            new CollectBallBySpeed(ballCollector, () -> 1)),
        new MoveByPath(driveTrain, trajectoryGenerator, PATHS[1][1]),
        new ParallelDeadlineGroup(new ShootBySpeed(shooter, () -> 1), new WaitCommand(1))
    );
  }
}
