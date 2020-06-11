package robot.autonomous.commands;

import static robot.autonomous.AutonomousConstants.Paths.GET_TARGET_TO_THREE_BALLS_AT_GENERATOR_PATH;
import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballCollector.BallCollector;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.OpenAndCollect;
import robot.ballStopper.BallStopper;
import robot.ballTrigger.commands.LoadBall;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.MoveToPose;
import robot.loaderConveyor.LoaderConveyor;
import robot.storageConveyor.StorageConveyor;

public class TargetToThreeBallsAtGeneratorToInitiationLinePath extends SequentialCommandGroup {

  public TargetToThreeBallsAtGeneratorToInitiationLinePath(
      final DriveTrain driveTrain, /*final Shooter shooter, final Vision vision,*/
      final BallCollector ballCollector, final LoaderConveyor loaderConveyor,
      final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    super(
//        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()).withTimeout(SHOOTER_TIMER),
        sequence(
            new MoveToPose(driveTrain, GET_TARGET_TO_THREE_BALLS_AT_GENERATOR_PATH.getPoseAt(1)),
            new MoveToPose(driveTrain, GET_TARGET_TO_THREE_BALLS_AT_GENERATOR_PATH.getPoseAt(2)),
            new MoveToPose(driveTrain, GET_TARGET_TO_THREE_BALLS_AT_GENERATOR_PATH.getPoseAt(3))
                .deadlineWith(
                    new OpenAndCollect(ballCollector, () -> PERCENTAGE_OUTPUT)),
            new LoadBall(loaderConveyor, storageConveyor, ballStopper)),
        new CloseBallCollectorPistons(ballCollector),
        new MoveToPose(driveTrain, GET_TARGET_TO_THREE_BALLS_AT_GENERATOR_PATH.getPoseAt(4)));
//            new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()).withTimeout(SHOOTER_TIMER)));
  }
}
