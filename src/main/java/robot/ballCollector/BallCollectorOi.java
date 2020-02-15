package robot.ballCollector;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.ballCollector.commands.CollectAndCount;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectCondition;
import robot.ballCollector.commands.OpenBallCollectorPistons;

public final class BallCollectorOi {

    public BallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache driveJoystickAxisCache,
                           UniqueButtonCache driverJoystickButtonCache, final CollectAndCount collectAndCount) {
        final JoystickAxis collectAndCountAxis =
                driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
        collectAndCountAxis.whileActiveContinuous(new CollectCondition(collectAndCount, ballCollector));

    final Trigger openBallCollectorPistonsButton = driverJoystickButtonCache.createJoystickTrigger
        (XboxController.Button.kBumperRight.value);
    openBallCollectorPistonsButton.whenActive(new OpenBallCollectorPistons(ballCollector));

    final Trigger closeBallCollectorPistonsButton = driverJoystickButtonCache.createJoystickTrigger
        (XboxController.Button.kBumperLeft.value);
    closeBallCollectorPistonsButton.whenActive(new CloseBallCollectorPistons(ballCollector));
  }
}
