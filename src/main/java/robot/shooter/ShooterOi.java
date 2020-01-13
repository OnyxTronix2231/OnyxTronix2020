package robot.shooter;

import onyxTronix.JoystickAxis;
import onyxTronix.JoystickAxis.AxisMap;
import onyxTronix.UniqueAxisCache;
import robot.shooter.commands.ShootByPercentOutput;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache driveJoystickAxisCache, final Shooter shooter){
    final JoystickAxis shootBySpeedAxis = driveJoystickAxisCache.createJoystickTrigger(AxisMap.kRightTrigger.value);
    shootBySpeedAxis.whileActiveContinuous(new ShootByPercentOutput(shooter, shootBySpeedAxis::getRawAxis));
  }
}
