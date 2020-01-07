package robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import onyxTronix.JoystickTriggerFactory;
import onyxTronix.JoystickTriggerType;
import onyxTronix.UniqueTriggerCache;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
        XboxController driveJoystick = new XboxController(0);
        UniqueTriggerCache driveJoystickButtonCache = new UniqueTriggerCache(driveJoystick,
          new JoystickTriggerFactory(JoystickTriggerType.Button));
        UniqueTriggerCache driveJoystickAxisCache = new UniqueTriggerCache(driveJoystick,
          new JoystickTriggerFactory(JoystickTriggerType.Axis));
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
