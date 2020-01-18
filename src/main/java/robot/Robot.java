package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.turret.BasicTurretComponents;
import robot.turret.TurretOi;
import robot.ballCollector.BallCollector;
import robot.ballCollector.BallCollectorOi;
import robot.ballCollector.BasicBallCollectorComponents;
import robot.drivetrain.BasicDriveTrainComponents;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.DriveByDistance;
import robot.drivetrain.commands.DriveBySpeed;
import robot.turret.BasicTurretComponents;
import robot.turret.Turret;
import robot.yawControl.YawControl;
import robot.yawControl.YawControlOi;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
        XboxController driveJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
        UniqueButtonCache driveJoystickButtonCache = new UniqueButtonCache(driveJoystick);
        UniqueAxisCache driveJoystickAxisCache = new UniqueAxisCache(driveJoystick);

        XboxController buttonsJoystick = new XboxController(BUTTONS_JOYSTICK_PORT);
        UniqueButtonCache buttonsJoystickButtonCache = new UniqueButtonCache(buttonsJoystick);
        UniqueAxisCache buttonsJoystickAxisCache = new UniqueAxisCache(buttonsJoystick);


        DriveTrain driveTrain = new DriveTrain(new BasicDriveTrainComponents());
        driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain, () -> driveJoystick.getY(GenericHID.Hand.kLeft),
            () -> -driveJoystick.getX(GenericHID.Hand.kRight)));

        YawControl yawControl = new YawControl(new BasicTurretComponents(), driveTrain);
        new YawControlOi(yawControl, buttonsJoystickButtonCache, buttonsJoystickAxisCache);

        new TurretOi(buttonsJoystickButtonCache, buttonsJoystickAxisCache, yawControl);

//        BallCollector ballCollector =new BallCollector(new BasicBallCollectorComponents());
//        new BallCollectorOi(ballCollector, buttonsJoystickAxisCache, buttonsJoystickButtonCache);

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
