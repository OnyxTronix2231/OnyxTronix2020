package robot.ballCollector;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectAndCount;
import robot.ballCounter.BallCounter;

public final class TestingBallCollectorOi {

  public TestingBallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache driverJoystickAxisCache,
                                UniqueAxisCache buttonsJoystickAxisCache,
                                final UniqueButtonCache driverJoystickButtonCache, final BallCounter ballCounter) {
    final Trigger driveOpenAndCollectCollectThenClose =
        driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    final Trigger buttonsOpenAndCollectThenClose =
        buttonsJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);

    final Trigger openAndCollectThenCloseButton = buttonsOpenAndCollectThenClose.or(driveOpenAndCollectCollectThenClose);
    openAndCollectThenCloseButton.whileActiveContinuous(new CollectAndCount(ballCounter, ballCollector));

    openAndCollectThenCloseButton.whenInactive(new CloseBallCollectorPistons(ballCollector));
  }
}
