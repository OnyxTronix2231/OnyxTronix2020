package robot.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveByJoystick extends DriveBySpeed {
  public DriveByJoystick( final DriveTrain driveTrain, final XboxController driveJoystick){
    super(driveTrain, () -> -driveJoystick.getY(GenericHID.Hand.kLeft), () -> driveJoystick.getX(GenericHID.Hand.kRight));
  }
}
