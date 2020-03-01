package robot.turret;

import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.turret.commands.MoveTurretByJoystick;

public class TurretOiBinder {

  public TurretOiBinder(final Turret turret, JoystickAxis moveTurretByAxis) {

    moveTurretByAxis.whileActiveContinuous(new MoveTurretByJoystick(turret,
        moveTurretByAxis::getRawAxis));
  }
}
