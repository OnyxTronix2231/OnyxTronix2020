package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.drivetrain.BasicDriveTrainComponents;
import robot.drivetrain.DriveTrain;

import java.util.Map;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
        XboxController driveJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
        UniqueButtonCache driveJoystickButtonCache = new UniqueButtonCache(driveJoystick);
        UniqueAxisCache driveJoystickAxisCache = new UniqueAxisCache(driveJoystick);

        XboxController buttonsJoystick = new XboxController(BUTTONS_JOYSTICK_PORT);
        UniqueButtonCache buttonsJoystickButtonCache = new UniqueButtonCache(buttonsJoystick);
        UniqueAxisCache buttonsJoystickAxisCache = new UniqueAxisCache(buttonsJoystick);

        final DriveTrain driveTrain = new DriveTrain(new BasicDriveTrainComponents());

        final SendableChooser<Integer> pathChooser = new SendableChooser<>();
        pathChooser.addOption("Auto2", 2);
      pathChooser.addOption("Auto3", 3);
      pathChooser.addOption("Auto4", 4);
      pathChooser.setDefaultOption("Auto1", 1);
      SmartDashboard.putData(pathChooser);

        Shuffleboard.getTab("Odometry").add("Target X:", 1).getEntry().addListener(
        x -> driveTrain.setOdometryTargetX(x.value.getDouble()), EntryListenerFlags.kUpdate);
        Shuffleboard.getTab("Odometry").add("Target Y:", 0).getEntry().addListener(
        y -> driveTrain.setOdometryTargetY(y.value.getDouble()), EntryListenerFlags.kUpdate);
        Shuffleboard.getTab("Odometry").add("Target Angle:", 0).getEntry().addListener(
        angle -> driveTrain.setOdometryTargetAngle(angle.value.getDouble()), EntryListenerFlags.kUpdate);
        Shuffleboard.getTab("Odometry").add("ComboBox", pathChooser).withWidget(BuiltInWidgets.kComboBoxChooser);
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
