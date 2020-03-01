package robot.drivetrain;

import onyxTronix.JoystickAxis;
import robot.drivetrain.commands.DriveBySpeed;

public class DriveTrainOiBinder {
  public DriveTrainOiBinder(final DriveTrain driveTrain, final JoystickAxis forwardAxis,
                            final JoystickAxis rotateAxis) {
    driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain, () -> -forwardAxis.getRawAxis(), rotateAxis::getRawAxis));
  }
}
