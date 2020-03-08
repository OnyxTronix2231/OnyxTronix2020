package robot.drivetrain.commands;

import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.Paths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballCollector.BallCollector;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenAndCollect;
import robot.ballCollector.commands.OpenBallCollectorPistons;
import robot.ballStopper.BallStopper;
import robot.ballTrigger.commands.LoadBall;
import robot.drivetrain.DriveTrain;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByDistance;
import robot.storageConveyor.StorageConveyor;
import robot.vision.Vision;

public class FirstPath extends SequentialCommandGroup {

  public FirstPath(final DriveTrain driveTrain, final Shooter shooter, final Vision vision,
                   final BallCollector ballCollector, final LoaderConveyor loaderConveyor,
                   final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    super(
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()).withTimeout(3),
        sequence(
            new MoveToPose(driveTrain, Paths.FIRST_PATH()[0].getEndingPose()),
            new MoveToPose(driveTrain, Paths.FIRST_PATH()[1].getEndingPose()),
            new MoveToPose(driveTrain, Paths.FIRST_PATH()[2].getEndingPose()))
            .deadlineWith(
            new OpenAndCollect(new OpenBallCollectorPistons(ballCollector),
                new CollectBallBySpeed(ballCollector, () -> PERCENTAGE_OUTPUT)),
            new LoadBall(loaderConveyor, storageConveyor, ballStopper)),
        new CloseBallCollectorPistons(ballCollector),
        new MoveToPose(driveTrain, Paths.FIRST_PATH()[3].getEndingPose()),
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()));
  }
}
