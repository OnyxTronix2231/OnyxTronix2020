package robot.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveByJoystick extends DriveBySpeed {
  public DriveByJoystick(DriveTrain driveTrain, XboxController driveJoystick){
    super(driveTrain, () -> -driveJoystick.getY(GenericHID.Hand.kLeft) * 0.8, () -> driveJoystick.getX(GenericHID.Hand.kRight) * 0.8);
  }
}
