package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
<<<<<<< HEAD
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
=======
import onyxTronix.JoystickTriggerFactory;
import onyxTronix.JoystickTriggerType;
import onyxTronix.UniqueTriggerCache;
import robot.drivetrain.BasicDriveTrainComponents;
import robot.drivetrain.DriveByJoystick;
import robot.drivetrain.DriveTrain;
import robot.vision.VisionOi;
import robot.vision.limelight.Limelight;
import robot.vision.limelight.enums.LimelightLedMode;
import robot.vision.limelight.enums.LimelightOperationMode;
>>>>>>> added basic vision + created a wrapper for Limelight's NetworkTables api

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
        XboxController driveJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
        UniqueButtonCache driveJoystickButtonCache = new UniqueButtonCache(driveJoystick);
        UniqueAxisCache driveJoystickAxisCache = new UniqueAxisCache(driveJoystick);

        XboxController buttonsJoystick = new XboxController(BUTTONS_JOYSTICK_PORT);
<<<<<<< HEAD
        UniqueButtonCache buttonsJoystickButtonCache = new UniqueButtonCache(buttonsJoystick);
        UniqueAxisCache buttonsJoystickAxisCache = new UniqueAxisCache(buttonsJoystick);
=======
        UniqueTriggerCache buttonsJoystickButtonCache = new UniqueTriggerCache(buttonsJoystick,
          new JoystickTriggerFactory(JoystickTriggerType.Button));
        UniqueTriggerCache buttonsJoystickAxisCache = new UniqueTriggerCache(buttonsJoystick,
          new JoystickTriggerFactory(JoystickTriggerType.Axis));

        Limelight.getInstance().setLedMode(LimelightLedMode.forceOff);
        Limelight.getInstance().setOperationMode(LimelightOperationMode.visionProcessor);

        DriveTrain driveTrain = new DriveTrain(new BasicDriveTrainComponents());
        driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));
        new VisionOi(driveJoystickButtonCache, driveJoystick, driveTrain);
>>>>>>> added basic vision + created a wrapper for Limelight's NetworkTables api
    }

    @Override
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void testInit() {
        LiveWindow.setEnabled(true);
    }

    @Override
    public void disabledInit() {
        LiveWindow.setEnabled(false);
    }

    @Override
    public void disabledPeriodic() {
        CommandScheduler.getInstance().run();
    }
}
