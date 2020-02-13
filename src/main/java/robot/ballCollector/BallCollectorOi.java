package robot.ballCollector;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.ballCollector.commands.ClosePistons;
import robot.ballCollector.commands.CollectAndCount;
import robot.ballCollector.commands.OpenPistons;

public final class BallCollectorOi {

    public BallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache driveJoystickAxisCache, UniqueTriggerCache buttonsJoystickButtonCache) {
        final JoystickAxis collectBySpeedAxis =
                driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
        collectBySpeedAxis.whileActiveContinuous(new CollectAndCount(ballCollector, () -> 1));

    final Trigger openPistonsButton = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
    openPistonsButton.whenActive(new OpenPistons(ballCollector));

    final Trigger closePistonsButton = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
    closePistonsButton.whenActive(new ClosePistons(ballCollector));
  }
}
