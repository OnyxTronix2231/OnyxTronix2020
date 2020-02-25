package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.PERCENT_OUTPUT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenAndCollect;
import robot.ballCollector.commands.OpenBallCollectorPistons;
import robot.crossSubsystem.commands.WaitUntilBallInLoader;
import robot.loaderConveyor.LoaderConveyor;

public final class TestingBallCollectorOi {

  public TestingBallCollectorOi(final BallCollector ballCollector, final LoaderConveyor loaderConveyor,
                                final UniqueAxisCache driverJoystickAxisCache,
                                UniqueAxisCache buttonsJoystickAxisCache,
                                final UniqueButtonCache driverJoystickButtonCache) {
    final Trigger driveOpenAndCollectCollectThenClose =
        driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    final Trigger buttonsOpenAndCollectThenClose =
        buttonsJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);

    final Trigger openAndCollectThenCloseButton = buttonsOpenAndCollectThenClose.or(driveOpenAndCollectCollectThenClose);
    openAndCollectThenCloseButton.whileActiveContinuous(new OpenAndCollect(new OpenBallCollectorPistons(ballCollector),
        new CollectBallBySpeed(ballCollector, () -> PERCENT_OUTPUT)));
    openAndCollectThenCloseButton.whenInactive(new CloseBallCollectorPistons(ballCollector));
  }
}
