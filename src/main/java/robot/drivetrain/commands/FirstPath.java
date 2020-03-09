package robot.drivetrain.commands;

import static robot.autonomous.AutonomousConstants.SHOOTER_TIMER;
import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.autonomous.AutonomousConstants.Paths;
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
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()).withTimeout(SHOOTER_TIMER),
        sequence(
            new MoveToPose(driveTrain, Paths.START_INFRONT_OF_TARGET_COLLECT_THREE_BALLS_FROM_SHIELD_GENERATOR()[0].getEndingPose()),
            new MoveToPose(driveTrain, Paths.START_INFRONT_OF_TARGET_COLLECT_THREE_BALLS_FROM_SHIELD_GENERATOR()[1].getEndingPose()),
            new MoveToPose(driveTrain, Paths.START_INFRONT_OF_TARGET_COLLECT_THREE_BALLS_FROM_SHIELD_GENERATOR()[2].getEndingPose()))
            .deadlineWith(
            new OpenAndCollect(new OpenBallCollectorPistons(ballCollector),
                new CollectBallBySpeed(ballCollector, () -> PERCENTAGE_OUTPUT)),
            new LoadBall(loaderConveyor, storageConveyor, ballStopper)),
        new CloseBallCollectorPistons(ballCollector),
        new MoveToPose(driveTrain, Paths.START_INFRONT_OF_TARGET_COLLECT_THREE_BALLS_FROM_SHIELD_GENERATOR()[3].getEndingPose()),
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()).withTimeout(SHOOTER_TIMER));
  }
}
