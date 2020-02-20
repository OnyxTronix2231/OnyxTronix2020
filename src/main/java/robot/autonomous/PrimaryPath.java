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

public class PrimaryPath extends SequentialCommandGroup {

  public PrimaryPath(final Shooter shooter, final DriveTrain driveTrain,
                     final OnyxTrajectoryGenerator trajectoryGenerator, final BallCollector ballCollector) {
    addCommands(new ParallelDeadlineGroup(new ShootBySpeed(shooter, () -> 1), new WaitCommand(1)),
    new MoveByPath(driveTrain, trajectoryGenerator, PATHS[0][0]),
    new ParallelDeadlineGroup(new CollectBallBySpeed(ballCollector, () -> 1), new WaitCommand(1)),
    new MoveByPath(driveTrain, trajectoryGenerator, PATHS[0][1]),
    new MoveByPath(driveTrain, trajectoryGenerator, PATHS[0][2]),
    new ParallelDeadlineGroup(new CollectBallBySpeed(ballCollector, () -> 1), new WaitCommand(1)),
    new MoveByPath(driveTrain, trajectoryGenerator, PATHS[0][3]),
    new ParallelDeadlineGroup(new ShootBySpeed(shooter, () -> 1), new WaitCommand(1))
    );
  }
}
