package robot.ballStopper;

import static robot.ballStopper.BallStopperConstants.DELAY_TIME;
import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;

import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.ballStopper.commands.StopBallStopper;

public class TestingBallStopperOi {
  public TestingBallStopperOi(final BallStopper ballStopper, final UniqueButtonCache buttonsJoystickButtonCache) {
    final Trigger moveBallStopperBySpeed = buttonsJoystickButtonCache.createJoystickTrigger(Button.kBumperRight.value);
    moveBallStopperBySpeed.whileActiveContinuous(new MoveBallStopperBySpeed(ballStopper, () -> PERCENTAGE_OUTPUT,
        DELAY_TIME)).whenInactive(new StopBallStopper(ballStopper));
  }
}
