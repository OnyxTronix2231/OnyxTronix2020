package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.shooter.commands.ShootBySpeed;
import static robot.shooter.ShooterConstants.PERECENT_OUT_PUT;

public class ShooterOi {

    public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache, final Shooter shooter) {
      final JoystickAxis shootBySpeedAxis =
          buttonJoystickAxisCache.createJoystickTrigger(XboxController.Button.kX.value);
      shootBySpeedAxis.whileActiveContinuous(new ShootBySpeed(shooter,  ()-> PERECENT_OUT_PUT));
    }
  }
