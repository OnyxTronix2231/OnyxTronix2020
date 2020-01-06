package robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.BasicDriveTrainComponents;
import robot.drivetrain.commands.DriveBySpeed;

public class Robot extends TimedRobot {

    public DriveTrain driveTrain;
    public XboxController controller;
    @Override
    public void robotInit() {
        driveTrain = new DriveTrain(new BasicDriveTrainComponents());
        controller = new XboxController(0);
        driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain,() -> controller.getY(GenericHID.Hand.kLeft), () -> controller.getX((GenericHID.Hand.kRight)) ));
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
