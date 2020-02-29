package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainOiBinder;
import robot.shooter.Shooter;
import robot.shooter.ShooterOiBinder;
import robot.turret.Turret;
import robot.vision.Vision;
import robot.yawControl.YawControl;

public class Oi {
  public Oi(DriveTrain driveTrain, Vision vision, Shooter shooter, YawControl yawControl) {
    final XboxController driveJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
    final UniqueButtonCache driveJoystickButtonCache = new UniqueButtonCache(driveJoystick);
    final UniqueAxisCache driveJoystickAxisCache = new UniqueAxisCache(driveJoystick);

    final XboxController buttonsJoystick = new XboxController(BUTTONS_JOYSTICK_PORT);
    final UniqueButtonCache buttonsJoystickButtonCache = new UniqueButtonCache(buttonsJoystick);
    final UniqueAxisCache buttonsJoystickAxisCache = new UniqueAxisCache(buttonsJoystick);

    JoystickAxis driverForwardAxis = driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);
    JoystickAxis driverRotateAxis = driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);

    new DriveTrainOiBinder(driveTrain, driverForwardAxis, driverRotateAxis);

    final JoystickButton spinShooterWhileAligningDrive = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    final JoystickButton spinShooterWhileAligningButton = buttonsJoystickButtonCache.
        createJoystickTrigger(XboxController.Button.kA.value);

    final JoystickAxis shootWithLoaderTriggerByDistance =
        driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    final JoystickButton shootWithoutVision = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    new ShooterOiBinder(shooter,() -> vision.getOuterTarget().getDistance(),
        spinShooterWhileAligningDrive.or(spinShooterWhileAligningButton), shootWithLoaderTriggerByDistance,
        shootWithoutVision);
  }
}
