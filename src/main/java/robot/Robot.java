package robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import robot.drivetrain.BasicDriveTrainComponents;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.DriveBySpeed;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
//        XboxController driveJoystick = new XboxController(0);
//        DriveTrain driveTrain = new DriveTrain(new BasicDriveTrainComponents());
//        driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain,
//            () -> driveJoystick.getY(GenericHID.Hand.kLeft), () ->driveJoystick.getX(GenericHID.Hand.kRight)));
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
