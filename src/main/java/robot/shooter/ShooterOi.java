package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache buttonJoystickButtonCache, final Shooter shooter){

    final JoystickAxis ShootByVelocity =
        buttonJoystickButtonCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);

  }
}
