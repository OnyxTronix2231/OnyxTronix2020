package robot.ballCollector;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.ballCollector.commands.CollectBallBySpeed;

public class BallCollectorOi {

    public BallCollectorOi(BallCollector ballCollector, UniqueTriggerCache buttonJoystickButtonCache) {
        Trigger trigger = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
        trigger.whileActiveContinuous(new CollectBallBySpeed(ballCollector, speedSupplier));
    }
}
