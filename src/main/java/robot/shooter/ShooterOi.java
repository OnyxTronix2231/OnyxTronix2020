package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.shooter.commands.ShootByPercentOutput;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache driveJoystickAxisCache, final Shooter shooter){
    final JoystickAxis shootBySpeedAxis = driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    shootBySpeedAxis.whileActiveContinuous(new ShootByPercentOutput(shooter, shootBySpeedAxis::getRawAxis));
  }
}
