package robot.ballCollector;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenBallCollectorPistons;

public class BallCollectorOi {

  public BallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache buttonJoystickAxisCache,
                         final UniqueButtonCache buttonJoystickButtonCache) {

    Trigger ejectBalls = buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    ejectBalls.whileActiveContinuous(new CollectBallBySpeed(ballCollector, () ->-1));
    ejectBalls.whenActive(new OpenBallCollectorPistons(ballCollector));
    ejectBalls.whenInactive(new CloseBallCollectorPistons(ballCollector));

    Trigger openClosePistonButton = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    openClosePistonButton.whenActive(new OpenBallCollectorPistons(ballCollector));

    openClosePistonButton.whenInactive(new CloseBallCollectorPistons(ballCollector));
  }
}
