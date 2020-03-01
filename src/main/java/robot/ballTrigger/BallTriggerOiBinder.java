package robot.ballTrigger;

import static robot.ballTrigger.BallTriggerConstants.OVERRIDE_SHOT_TIMEOUT;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.ballTrigger.commands.MoveAllConveyors;
import robot.ballTrigger.commands.ReleaseBallByLoaderAsTrigger;
import robot.ballTrigger.commands.LoadBall;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;

import java.util.function.BooleanSupplier;

public class BallTriggerOiBinder {

  public BallTriggerOiBinder(final LoaderConveyor loaderConveyor, final StorageConveyor storageConveyor,
                             final BallStopper ballStopper, final BooleanSupplier canReleaseBallSupplier,
                             final BooleanSupplier canReleaseBallAtCloseRangeSupplier,
                             final Trigger loadBall, final Trigger triggerBall,
                             final Trigger triggerBallAtCloseRange, final Trigger triggerBallManually) {
    loadBall.whileActiveContinuous(new LoadBall(loaderConveyor, storageConveyor, ballStopper));

    triggerBall.and(triggerBallManually.negate()).whileActiveContinuous(
        new ReleaseBallByLoaderAsTrigger(loaderConveyor, storageConveyor, ballStopper,
            canReleaseBallSupplier));

    triggerBallAtCloseRange.and(triggerBallManually.negate()).whileActiveContinuous(
        new ReleaseBallByLoaderAsTrigger(loaderConveyor, storageConveyor, ballStopper,
            canReleaseBallAtCloseRangeSupplier));

    (triggerBall.or(triggerBallAtCloseRange)).and(triggerBallManually).whenActive(
        new MoveAllConveyors(loaderConveyor, storageConveyor, ballStopper,
            () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX,
            () -> StorageConveyorConstants.PERCENTAGE_OUTPUT, () -> BallStopperConstants.PERCENTAGE_OUTPUT)
            .withTimeout(OVERRIDE_SHOT_TIMEOUT));
  }
}
