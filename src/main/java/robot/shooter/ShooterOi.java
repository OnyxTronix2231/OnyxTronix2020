package robot.shooter;

import onyxTronix.JoystickAxis;
import onyxTronix.JoystickAxis.AxisMap;
import onyxTronix.UniqueAxisCache;
import robot.shooter.commands.ShootByPercentOutput;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache, final Shooter shooter){
    final JoystickAxis shootBySpeedAxis = buttonJoystickAxisCache.createJoystickTrigger(AxisMap.kLeftY.value);
    shootBySpeedAxis.whileActiveContinuous(new ShootByPercentOutput(shooter, shootBySpeedAxis::getRawAxis));
  }
}
