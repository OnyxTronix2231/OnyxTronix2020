package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.ballCollector.commands.ClosePistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenPistons;

public final class BallCollectorOi {

    public BallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache buttonJoystickAxisCache, UniqueTriggerCache buttonsJoystickButtonCache) {
        final Trigger collectBySpeedAxis =
                buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
        collectBySpeedAxis.whileActiveContinuous(new CollectBallBySpeed(ballCollector, () -> SPEED));

        final Trigger openDoubleSolenoidsButton = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperRight.value);a
        openDoubleSolenoidsButton.whenActive(new OpenPistons(ballCollector));

        final Trigger closeDoubleSolenoidsButton = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperLeft.value);
        closeDoubleSolenoidsButton.whenActive(new ClosePistons(ballCollector));
    }
}
