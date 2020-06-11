package robot.autonomous.commands;

import static robot.autonomous.AutonomousConstants.Paths.GET_THREE_BALLS_AT_TRENCH_AND_TWO_AT_GENERATOR;
import static robot.autonomous.AutonomousConstants.SHOOTER_TIMER;
import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballCollector.BallCollector;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenAndCollect;
import robot.ballCollector.commands.OpenBallCollectorPistons;
import robot.ballStopper.BallStopper;
import robot.ballTrigger.commands.LoadBall;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.MoveToPose;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByDistance;
import robot.storageConveyor.StorageConveyor;
import robot.vision.Vision;

public class TrenchRunToTwoBallsToThreeMorePath extends SequentialCommandGroup {

  public TrenchRunToTwoBallsToThreeMorePath(final DriveTrain driveTrain, final Shooter shooter, final Vision vision,
                                            final BallCollector ballCollector, final LoaderConveyor loaderConveyor,
                                            final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    super(
        new MoveToPose(driveTrain, GET_THREE_BALLS_AT_TRENCH_AND_TWO_AT_GENERATOR.getPoseAt(1))
            .deadlineWith(
                new OpenAndCollect(ballCollector, () -> PERCENTAGE_OUTPUT),
                new LoadBall(loaderConveyor, storageConveyor, ballStopper)),
        new CloseBallCollectorPistons(ballCollector),
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()).withTimeout(SHOOTER_TIMER),
        sequence(
            new MoveToPose(driveTrain, GET_THREE_BALLS_AT_TRENCH_AND_TWO_AT_GENERATOR.getPoseAt(2)),
            new MoveToPose(driveTrain, GET_THREE_BALLS_AT_TRENCH_AND_TWO_AT_GENERATOR.getPoseAt(3)),
            new MoveToPose(driveTrain, GET_THREE_BALLS_AT_TRENCH_AND_TWO_AT_GENERATOR.getPoseAt(4)))
            .deadlineWith(
                new OpenAndCollect(ballCollector, () -> PERCENTAGE_OUTPUT),
                new LoadBall(loaderConveyor, storageConveyor, ballStopper)),
        new CloseBallCollectorPistons(ballCollector),
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()).withTimeout(SHOOTER_TIMER)
    );
  }
}
