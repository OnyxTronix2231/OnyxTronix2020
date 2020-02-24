package robot.crossSubsystem;

import static robot.ballCollector.BallCollectorConstants.PERCENT_OUTPUT;

import edu.wpi.first.wpilibj.XboxController;
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
  public SmartBallCollectorOi(final UniqueButtonCache driveJoystickButtonCache,
                              final UniqueAxisCache driveJoystickAxisCache,
                              final BallCollector ballCollector, final LoaderConveyor loaderConveyor,
                              final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    final Trigger openAndCollectThenCloseButton = driveJoystickAxisCache.createJoystickTrigger(
        XboxController.Axis.kLeftTrigger.value);
    openAndCollectThenCloseButton.whileActiveContinuous(new OpenAndCollect(new OpenBallCollectorPistons(ballCollector),
        new CollectBallBySpeed(ballCollector, () -> PERCENT_OUTPUT)));
    openAndCollectThenCloseButton.whileActiveContinuous(new MoveConveyorsUntilBallInLoader(loaderConveyor, ballStopper,
        storageConveyor, LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX, BallStopperConstants.PERCENTAGE_OUTPUT,
        StorageConveyorConstants.PERCENTAGE_OUTPUT));
    openAndCollectThenCloseButton.whenInactive(new CloseBallCollectorPistons(ballCollector));
  }
}