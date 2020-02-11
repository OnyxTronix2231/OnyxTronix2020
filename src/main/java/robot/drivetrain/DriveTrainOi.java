package robot.drivetrain;

import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;

public class DriveTrainOi {

  public DriveTrainOi(final UniqueAxisCache driveJoystickAxisCache) {
    final JoystickAxis leftY = driveJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftY.value);
    final JoystickAxis rightX = driveJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kRightX.value);
  }
}
