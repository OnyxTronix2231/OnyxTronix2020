package robot.ballCollector;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.ballCollector.commands.CollectBallBySpeed;

public final class BallCollectorOi {

    public BallCollectorOi(final BallCollector ballCollector, final UniqueTriggerCache buttonJoystickButtonCache) {

        final Trigger collectBallButton = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
        collectBallButton.whileActiveContinuous(new CollectBallBySpeed(ballCollector,
                () -> BallCollectorConstant.SPEED));
    }
}
