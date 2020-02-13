package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.commands.ClosePistons;
import robot.ballCollector.commands.CollectBallBySpeed;
import robot.ballCollector.commands.OpenPistons;

public final class BallCollectorOi {

  public BallCollectorOi(final BallCollector ballCollector, final UniqueAxisCache driverJoystickAxisCache,
                         final UniqueButtonCache driverJoystickButtonCache) {
    final Trigger collectBySpeed =
        driverJoystickAxisCache.createJoystickTrigger(XboxController.Button.kB.value);
    collectBySpeed.whileActiveContinuous(new CollectBallBySpeed(ballCollector, ()-> SPEED));

    final Trigger openBallCollectorPistonsButton = driverJoystickButtonCache.createJoystickTrigger
        (XboxController.Button.kBumperRight.value);
    openBallCollectorPistonsButton.whenActive(new OpenPistons(ballCollector));

    final Trigger closeBallCollectorPistonsButton = driverJoystickButtonCache.createJoystickTrigger
        (XboxController.Button.kBumperLeft.value);
    closeBallCollectorPistonsButton.whenActive(new ClosePistons(ballCollector));
  }
}
