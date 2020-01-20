package robot.ballCollector;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.ballCollector.commands.CollectBallBySpeed;

public final class BallCollectorOi {

    public BallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache buttonJoystickAxisCache) {
        final JoystickAxis collectBySpeedAxis =
                buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
        collectBySpeedAxis.whileActiveContinuous(new CollectBallBySpeed(ballCollector, collectBySpeedAxis::getRawAxis));
    }
}
