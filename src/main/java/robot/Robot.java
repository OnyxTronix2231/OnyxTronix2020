package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import onyxTronix.UniqueTriggerCache;
import robot.turret.BasicTurretComponents;
import robot.turret.Turret;
import robot.turret.TurretOi;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
        XboxController driveJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
        UniqueButtonCache driveJoystickButtonCache = new UniqueButtonCache(driveJoystick);
        UniqueAxisCache driveJoystickAxisCache = new UniqueAxisCache(driveJoystick);

        XboxController buttonsJoystick = new XboxController(BUTTONS_JOYSTICK_PORT);
        UniqueButtonCache buttonsJoystickButtonCache = new UniqueButtonCache(buttonsJoystick);
        UniqueAxisCache buttonsJoystickAxisCache = new UniqueAxisCache((buttonsJoystick));

        Turret turret = new Turret(new BasicTurretComponents());
        new TurretOi(driveJoystickButtonCache, driveJoystickAxisCache, turret);
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
