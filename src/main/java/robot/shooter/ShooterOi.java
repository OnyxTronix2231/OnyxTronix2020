package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.ShootByPercentOutput;

public class ShooterOi {

    public ShooterOi(final Shooter shooter, final UniqueAxisCache buttonJoystickAxisCache,
                           UniqueButtonCache buttonsJoystickButtonCache) {
      final Trigger shootBySpeedAxis =
          buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
      shootBySpeedAxis.whileActiveContinuous(new ShootByPercentOutput(shooter,  ()-> 1));
    }
  }



