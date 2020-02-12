package robot.turret;

import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.turret.commands.MoveTurretBySpeed;

public class TurretOi {

  public TurretOi(final UniqueAxisCache buttonJoystickAxisCache, final Turret turret) {
    final JoystickAxis moveBySpeedAxis =
        buttonJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftY.value);
    moveBySpeedAxis.whileActiveContinuous(new MoveTurretBySpeed(turret, moveBySpeedAxis::getRawAxis));
  }
}
