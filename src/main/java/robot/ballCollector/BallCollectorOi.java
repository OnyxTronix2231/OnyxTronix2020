package robot.ballCollector;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.commands.CloseBallCollectorPistons;
import robot.ballCollector.commands.OpenBallCollectorPistons;

public final class BallCollectorOi {

  public BallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache driverJoystickAxisCache,
                         final UniqueButtonCache driverJoystickButtonCache) {

    final Trigger openBallCollectorPistonsButton = driverJoystickButtonCache.createJoystickTrigger
        (XboxController.Button.kBumperRight.value);
    openBallCollectorPistonsButton.whenActive(new OpenBallCollectorPistons(ballCollector));

    final Trigger closeBallCollectorPistonsButton = driverJoystickButtonCache.createJoystickTrigger
        (XboxController.Button.kBumperLeft.value);
    closeBallCollectorPistonsButton.whenActive(new CloseBallCollectorPistons(ballCollector));
  }
}
