package robot.turret;

import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.turret.commands.MoveBySpeed;

public class TurretOi {

  public TurretOi(final UniqueAxisCache buttonJoystickAxisCache, final Turret turret) {
    final JoystickAxis moveBySpeedAxis =
        buttonJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftY.value);
    moveBySpeedAxis.whileActiveContinuous(new MoveBySpeed(turret, moveBySpeedAxis::getRawAxis));
  }
}
