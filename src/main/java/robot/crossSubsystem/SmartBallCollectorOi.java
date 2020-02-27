package robot.crossSubsystem;

import static robot.ballCollector.BallCollectorConstants.CLOSING_SEQUENCE_DELAY;
import static robot.ballCollector.BallCollectorConstants.CLOSING_SEQUENCE_TIMEOUT;
import static robot.ballCollector.BallCollectorConstants.DURING_CLOSED_PERCENT_OUTPUT;
import static robot.ballCollector.BallCollectorConstants.PERCENT_OUTPUT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.BallCollector;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenAndCollect;
import robot.ballCollector.commands.OpenBallCollectorPistons;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.crossSubsystem.commands.MoveConveyorsUntilBallInLoader;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;

public class SmartBallCollectorOi {
  public SmartBallCollectorOi(final UniqueButtonCache driveJoystickButtonCache, final UniqueAxisCache buttonJoystickUniqueAxisCache,
                              final UniqueAxisCache driveJoystickAxisCache,
                              final BallCollector ballCollector, final LoaderConveyor loaderConveyor,
                              final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    final Trigger openAndCollectDriveStick = buttonJoystickUniqueAxisCache.createJoystickTrigger(
        XboxController.Axis.kLeftTrigger.value);
    final Trigger openAndCollectButtonStick = driveJoystickAxisCache.createJoystickTrigger(
        XboxController.Axis.kLeftTrigger.value);

    final Trigger openAndCollectTrigger = openAndCollectButtonStick.or(openAndCollectDriveStick);
    openAndCollectTrigger.whileActiveContinuous(new OpenAndCollect(new OpenBallCollectorPistons(ballCollector),
        new CollectBallBySpeed(ballCollector, () -> PERCENT_OUTPUT)));

    openAndCollectTrigger.whileActiveContinuous(new MoveConveyorsUntilBallInLoader(loaderConveyor, ballStopper,
        storageConveyor, () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX, () ->
        StorageConveyorConstants.PERCENTAGE_OUTPUT,
        () -> BallStopperConstants.PERCENTAGE_OUTPUT));

    openAndCollectTrigger.whenInactive(new CloseBallCollectorPistons(ballCollector).andThen(new WaitCommand(
        CLOSING_SEQUENCE_DELAY))
        .andThen(new CollectBallBySpeed(ballCollector,
        () -> DURING_CLOSED_PERCENT_OUTPUT).withTimeout(CLOSING_SEQUENCE_TIMEOUT)));
  }
}
