package robot.ballStopper;

import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;

import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.ballStopper.commands.MoveBallStopperBySpeed;

public class BallStopperOi {
  public BallStopperOi(final BallStopper ballStopper, final UniqueButtonCache buttonsJoystickButtonCache) {
    final Trigger moveBallStopperBySpeed = buttonsJoystickButtonCache.createJoystickTrigger(Button.kStart.value);
    moveBallStopperBySpeed.whileActiveContinuous(new MoveBallStopperBySpeed(ballStopper, () -> PERCENTAGE_OUTPUT));
  }
}