package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.LoaderConveyor.BasicLoaderConveyorComponents;
import robot.LoaderConveyor.LoaderConveyor;
import robot.ballCollector.BallCollector;
import robot.ballCollector.BallCollectorOi;
import robot.ballCollector.BasicBallCollectorComponents;
import robot.crossSubsystem.CrossSubsystemOi;
import robot.drivetrain.BasicDriveTrainComponents;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.DriveBySpeed;
import robot.shooter.BasicShooterComponents;
import robot.shooter.Shooter;
import robot.shooter.ShooterOi;
import robot.turret.BasicTurretComponents;
import robot.vision.VisionCalculations;
import robot.vision.VisionOi;
import robot.vision.limelight.Limelight;
import robot.vision.limelight.target.LimelightTarget;
import robot.yawControl.YawControl;
import robot.yawControl.YawControlOi;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
        XboxController driveJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
        UniqueButtonCache driveJoystickButtonCache = new UniqueButtonCache(driveJoystick);
        UniqueAxisCache driveJoystickAxisCache = new UniqueAxisCache(driveJoystick);

        XboxController buttonsJoystick = new XboxController(BUTTONS_JOYSTICK_PORT);
        UniqueButtonCache buttonsJoystickButtonCache = new UniqueButtonCache(buttonsJoystick);
        UniqueAxisCache buttonsJoystickAxisCache = new UniqueAxisCache(buttonsJoystick);
        BallCollector ballCollector = new BallCollector(new BasicBallCollectorComponents());
        new BallCollectorOi(ballCollector, buttonsJoystickAxisCache, buttonsJoystickButtonCache);

        LoaderConveyor loaderConveyor =  new LoaderConveyor(new BasicLoaderConveyorComponents());
        Shooter shooter = new Shooter(new BasicShooterComponents());
        new CrossSubsystemOi(driveJoystickAxisCache, driveJoystickButtonCache, shooter, loaderConveyor);

        DriveTrain driveTrain = new DriveTrain(new BasicDriveTrainComponents());
        driveTrain.setDefaultCommand(new DriveBySpeed(driveTrain,
            () -> driveJoystick.getY(GenericHID.Hand.kLeft), () -> -driveJoystick.getX(GenericHID.Hand.kRight)));

        new ShooterOi(shooter, buttonsJoystickAxisCache, driveJoystickButtonCache);

        Shuffleboard.getTab("Shooter").addNumber("Limelight Angle", this::getLimelightAngle);
        Shuffleboard.getTab("Shooter").addNumber("Limelight Distance", this::getLimelightDistance);

    }

    public double getLimelightAngle() {
        if(Limelight.getInstance().targetFound()) {
            return Limelight.getInstance().getTarget().getHorizontalOffsetToCrosshair();
        }
        return 0;
    }

    public double getLimelightDistance() {
        if(Limelight.getInstance().targetFound()) {
            return VisionCalculations.calculateDistance(Limelight.getInstance().getTarget());
        }
        return 0;
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
