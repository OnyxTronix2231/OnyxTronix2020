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
                             final BooleanSupplier canReleaseBallAtCloseRangeSupplier, final Trigger loadBall,
                             final Trigger releaseBall, final Trigger releaseBallAtCloseRange,
                             final Trigger releaseBallManually) {

    loadBall.whileActiveContinuous(new LoadBall(loaderConveyor, storageConveyor,
        ballStopper));

    releaseBall.and(releaseBallManually.negate()).whileActiveContinuous(
        new ReleaseBallByLoaderAsTrigger(loaderConveyor, storageConveyor, ballStopper,
            canReleaseBallSupplier));

    releaseBallAtCloseRange.and(releaseBallManually.negate()).whileActiveContinuous(
        new ReleaseBallByLoaderAsTrigger(loaderConveyor, storageConveyor, ballStopper,
            canReleaseBallAtCloseRangeSupplier));

    (releaseBall.or(releaseBallAtCloseRange)).and(releaseBallManually).whenActive(
        new MoveAllConveyors(loaderConveyor, storageConveyor, ballStopper,
            () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX,
            () -> StorageConveyorConstants.PERCENTAGE_OUTPUT, () -> BallStopperConstants.PERCENTAGE_OUTPUT)
            .withTimeout(OVERRIDE_SHOT_TIMEOUT));
  }
}
