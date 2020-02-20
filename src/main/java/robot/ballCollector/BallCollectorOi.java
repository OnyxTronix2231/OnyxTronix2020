package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.PERCENT_OUTPUT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenBallCollectorPistons;

public final class BallCollectorOi {

  public BallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache driverJoystickAxisCache,
                         UniqueAxisCache buttonsJoystickAxisCache,
                         final UniqueButtonCache driverJoystickButtonCache) {
    final Trigger driveCollectBySpeed =
        driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    final Trigger buttonsCollectBySpeed =
        buttonsJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);

    buttonsCollectBySpeed.or(driveCollectBySpeed).
        whileActiveContinuous(new CollectBallBySpeed(ballCollector, () -> PERCENT_OUTPUT));
  }
}
