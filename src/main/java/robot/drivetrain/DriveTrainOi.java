package robot.drivetrain;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.drivetrain.commands.DriveBySpeed;

public class DriveTrainOi {

  public DriveTrainOi(final DriveTrain driveTrain, final UniqueAxisCache driveJoystickAxisCache) {
    final JoystickAxis leftY = driveJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftY.value);
    final JoystickAxis rightX = driveJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kRightX.value);

//    leftY.or(rightX).whileActiveContinuous(new DriveBySpeed(driveTrain, leftY::getRawAxis, () -> -rightX.getRawAxis()));
  }
}
