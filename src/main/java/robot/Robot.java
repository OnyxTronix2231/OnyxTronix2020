package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;

public class Robot extends TimedRobot {

    ColorSensorV3 colorSensorV3;

    @Override
    public void robotInit() {
        XboxController driveJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
        UniqueButtonCache driveJoystickButtonCache = new UniqueButtonCache(driveJoystick);
        UniqueAxisCache driveJoystickAxisCache = new UniqueAxisCache(driveJoystick);

        XboxController buttonsJoystick = new XboxController(BUTTONS_JOYSTICK_PORT);
        UniqueButtonCache buttonsJoystickButtonCache = new UniqueButtonCache(buttonsJoystick);
        UniqueAxisCache buttonsJoystickAxisCache = new UniqueAxisCache(buttonsJoystick);
    }

    @Override
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
        Color color = colorSensorV3.getColor();
        SmartDashboard.putNumber("Red", color.red);
        SmartDashboard.putNumber("Green", color.green);
        SmartDashboard.putNumber("Blue", color.blue);

        SmartDashboard.putNumber("How Red", howRed(color));
        SmartDashboard.putNumber("How Green", howGreen(color));
        SmartDashboard.putNumber("How Blue", howBlue(color));
        SmartDashboard.putNumber("How Yellow", howYellow(color));
        SmartDashboard.putString("What Color", theColor(color));
    }

    public double squaredDiff(double a, double b) {
        return Math.pow(a-b,2);
    }

    public double howBlue(Color color) {
        return (squaredDiff(color.red, 0.108) + squaredDiff(color.green, 0.428) + squaredDiff(color.blue, 0.462));
    }

    public double howRed(Color color) {
        return (squaredDiff(color.red, 0.551) + squaredDiff(color.green, 0.329) + squaredDiff(color.blue, 0.119));
    }

    public double howGreen(Color color) {
        return (squaredDiff(color.red, 0.149) + squaredDiff(color.green, 0.599) + squaredDiff(color.blue, 0.250));
    }

    public double howYellow(Color color) {
        return (squaredDiff(color.red, 0.319) + squaredDiff(color.green, 0.571) + squaredDiff(color.blue, 0.108));
    }

    public String theColor(Color color) {
        double green = howGreen(color);
        double blue = howBlue(color);
        double red = howRed(color);
        double yellow = howYellow(color);

        double min = Math.min(green, Math.min(blue, Math.min(red, yellow)));
        if (min == green) {
            return "Green";
        } else if (min == blue) {
            return "Blue";
        } else if (min == red) {
            return "Red";
        } else {
            return "Yellow";
        }
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
