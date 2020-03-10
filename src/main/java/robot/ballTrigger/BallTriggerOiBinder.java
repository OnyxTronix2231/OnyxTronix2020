package robot.ballTrigger;

import static robot.ballStopper.BallStopperConstants.MOVE_STOPPER_BACK;
import static robot.ballTrigger.BallTriggerConstants.BALL_STOPPER_SPEED;
import static robot.ballTrigger.BallTriggerConstants.LOADER_SPEED;
import static robot.ballTrigger.BallTriggerConstants.OVERRIDE_SHOT_TIMEOUT;
import static robot.ballTrigger.BallTriggerConstants.STORAGE_REVERSE_TIMEOUT;
import static robot.ballTrigger.BallTriggerConstants.STORAGE_SPEED;
import static robot.loaderConveyor.LoaderConveyorConstants.MOVE_LOADER_BACK;
import static robot.storageConveyor.StorageConveyorConstants.MOVE_STORAGE_BACK;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.ballTrigger.commands.MoveAllConveyors;
import robot.ballTrigger.commands.ReleaseBallByLoaderAsTrigger;
import robot.ballTrigger.commands.LoadBall;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.BooleanSupplier;

public class BallTriggerOiBinder {

  public BallTriggerOiBinder(final LoaderConveyor loaderConveyor, final StorageConveyor storageConveyor,
                             final BallStopper ballStopper, final BooleanSupplier canReleaseBallSupplier,
                             final BooleanSupplier canReleaseBallAtCloseRangeSupplier,
                             final Trigger loadBall, final Trigger triggerBall,
                             final Trigger triggerBallAtCloseRange, final Trigger triggerBallManually,
                             final Trigger moveConveyorsReverse) {
    loadBall.whileActiveContinuous(new LoadBall(loaderConveyor, storageConveyor, ballStopper));

    triggerBall.and(triggerBallManually.negate()).whileActiveContinuous(
        new ReleaseBallByLoaderAsTrigger(loaderConveyor, storageConveyor, ballStopper,
            canReleaseBallSupplier));

    triggerBallAtCloseRange.and(triggerBallManually.negate()).whileActiveContinuous(
        new WaitCommand(0.5).andThen(new ReleaseBallByLoaderAsTrigger(loaderConveyor, storageConveyor, ballStopper,
            canReleaseBallAtCloseRangeSupplier)));

    triggerBall.or(triggerBallAtCloseRange).and(triggerBallManually).whenActive(
        new MoveAllConveyors(loaderConveyor, storageConveyor, ballStopper,
            () -> LOADER_SPEED, () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED).withTimeout(OVERRIDE_SHOT_TIMEOUT));

    moveConveyorsReverse.whileActiveContinuous(new ParallelCommandGroup(
        new MoveLoaderConveyorBySpeed(loaderConveyor, () -> MOVE_LOADER_BACK),
        new MoveBallStopperBySpeed(ballStopper, () -> MOVE_STOPPER_BACK),
        new MoveStorageConveyorBySpeed(storageConveyor, () -> MOVE_STORAGE_BACK).withTimeout(STORAGE_REVERSE_TIMEOUT)));
  }
}
