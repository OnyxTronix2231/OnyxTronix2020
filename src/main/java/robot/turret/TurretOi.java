package robot.turret;

import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.turret.commands.ChangeAngleOffsetByPercent;

public class TurretOi {

  public TurretOi(final Turret turret, final UniqueAxisCache buttonJoystickAxisCache) {

    final JoystickAxis changeAngleByPercentAxis =
        buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightX.value);
    changeAngleByPercentAxis.whileActiveContinuous(new ChangeAngleOffsetByPercent(turret,
        changeAngleByPercentAxis::getRawAxis));
  }
}
