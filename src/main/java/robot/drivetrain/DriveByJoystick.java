package robot.drivetrain;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveByJoystick extends CommandBase {

  private final DriveTrain driveTrain;
  private final XboxController driveJoystick;

  public DriveByJoystick(final DriveTrain driveTrain, final XboxController driveJoystick) {
    this.driveTrain = driveTrain;
    this.driveJoystick = driveJoystick;
    addRequirements(driveTrain);
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(driveJoystick.getY(GenericHID.Hand.kLeft), driveJoystick.getX(GenericHID.Hand.kRight));
  }
}
