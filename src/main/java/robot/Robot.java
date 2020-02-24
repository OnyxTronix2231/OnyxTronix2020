package robot;

import static robot.RobotConstants.ALIGNING_TIME_OUT;
import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;
import static robot.RobotConstants.ROBOT_TYPE;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.BallCollector;
import robot.ballCollector.BallCollectorComponents;
import robot.ballCollector.TestingBallCollectorOi;
import robot.ballCollector.BasicBallCollectorComponentsA;
import robot.ballCounter.BallCounter;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperComponents;
import robot.ballStopper.BasicBallStopperComponentsA;
import robot.basicautonomous.AutonomousShootCommand;
import robot.crossSubsystem.SmartShooterOi;
import robot.crossSubsystem.commands.SpinShooterAndLoaderByDistance;
import robot.drivetrain.BasicDriveTrainComponentsA;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainComponents;
import robot.drivetrain.commands.DriveByDistance;
import robot.drivetrain.commands.DriveByJoystick;
import robot.loaderConveyor.BasicLoaderConveyorComponentsA;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorComponents;
import robot.loaderConveyor.TestingLoaderConveyorOi;
import robot.shooter.BasicShooterComponentsA;
import robot.shooter.Shooter;
import robot.shooter.ShooterComponents;
import robot.shooter.TestingShooterOi;
import robot.shooter.commands.ShootAndCount;
import robot.storageConveyor.BasicStorageConveyorComponentsA;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorComponents;
import robot.storageConveyor.TestingStorageConveyorOi;
import robot.turret.BasicTurretComponentsA;
import robot.turret.TurretComponents;
import robot.turret.TestingTurretOi;
import robot.turret.commands.MoveTurretByAngle;
import robot.vision.Vision;
import robot.vision.VisionConstants;
import robot.vision.target.VisionTargetFactory;
import robot.yawControl.YawControl;
import robot.yawControl.YawControlOi;
import robot.yawControl.commands.AlignByVisionOrOdometryAndVision;
import vision.limelight.Limelight;

public class Robot extends TimedRobot {

  private AutonomousShootCommand autonomousShootCommand;

  @Override
  public void robotInit() {
    LiveWindow.disableAllTelemetry();
    final XboxController driveJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
    final UniqueButtonCache driveJoystickButtonCache = new UniqueButtonCache(driveJoystick);
    final UniqueAxisCache driveJoystickAxisCache = new UniqueAxisCache(driveJoystick);

    final XboxController buttonsJoystick = new XboxController(BUTTONS_JOYSTICK_PORT);
    final UniqueButtonCache buttonsJoystickButtonCache = new UniqueButtonCache(buttonsJoystick);
    final UniqueAxisCache buttonsJoystickAxisCache = new UniqueAxisCache(buttonsJoystick);

    final DriveTrainComponents driveTrainComponents;
    final BallCollectorComponents ballCollectorComponents;
    final BallStopperComponents ballStopperComponents;
    final StorageConveyorComponents storageConveyorComponents;
    final TurretComponents turretComponents;
    final LoaderConveyorComponents loaderConveyorComponents;
    final ShooterComponents shooterComponents;
    final BallCounter ballCounter = new BallCounter();

    if (ROBOT_TYPE == RobotType.A) {
      driveTrainComponents = new BasicDriveTrainComponentsA();
      ballCollectorComponents = new BasicBallCollectorComponentsA();
      ballStopperComponents = new BasicBallStopperComponentsA();
      storageConveyorComponents = new BasicStorageConveyorComponentsA();
      turretComponents = new BasicTurretComponentsA();
      loaderConveyorComponents = new BasicLoaderConveyorComponentsA();
      shooterComponents = new BasicShooterComponentsA();
    } else {
      driveTrainComponents = null; //TODO: use BasicDriveTrainComponentsB Here
      ballCollectorComponents = null; //TODO: use BasicBallCollectorComponentsB Here
      ballStopperComponents = null; //TODO: use BasicBallStopperComponentsB Here
      storageConveyorComponents = null; //TODO: use BasicStorageConveyorComponentsB Here
      turretComponents = null;  //TODO: use BasicTurretComponentsB Here
      loaderConveyorComponents = null; //TODO: use BasicLoaderConveyorComponentsB Here
      shooterComponents = null; //TODO: use BasicShooterComponentsB Here
    }

    final DriveTrain driveTrain = new DriveTrain(driveTrainComponents);
    driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));

    final BallCollector ballCollector = new BallCollector(ballCollectorComponents);
    new TestingBallCollectorOi(ballCollector, driveJoystickAxisCache, buttonsJoystickAxisCache, driveJoystickButtonCache, ballCounter);

    final BallStopper ballStopper = new BallStopper(ballStopperComponents);

    final StorageConveyor storageConveyor = new StorageConveyor(storageConveyorComponents);
    new TestingStorageConveyorOi(storageConveyor, driveJoystickButtonCache);

    final YawControl yawControl = new YawControl(turretComponents, driveTrain);
    new TestingTurretOi(yawControl, buttonsJoystickAxisCache);

    final LoaderConveyor loaderConveyor = new LoaderConveyor(loaderConveyorComponents);
    new TestingLoaderConveyorOi(loaderConveyor, buttonsJoystickButtonCache);

    final Shooter shooter = new Shooter(shooterComponents);
    new TestingShooterOi(buttonsJoystickAxisCache, driveJoystickButtonCache, shooter);

    Vision vision = new Vision(new VisionTargetFactory(yawControl::getAngleRTR,
        driveTrain::getOdometryHeading,
        VisionConstants.RobotAConstants.CAMERA_VERTICAL_OFFSET_ANGLE,
        VisionConstants.RobotAConstants.CAMERA_HEIGHT_CM, Limelight.getInstance()));

    new SmartShooterOi(driveJoystickButtonCache, driveJoystickAxisCache, shooter, loaderConveyor,
        storageConveyor, ballStopper, ballCollector, vision, ballCounter);

    new YawControlOi(yawControl, driveTrain, vision::getOuterTarget, buttonsJoystickButtonCache, driveJoystickButtonCache);

    Shuffleboard.getTab("Shooter").addNumber("Velocity by distance",
        () -> shooter.distanceToVelocity(vision.getOuterTarget().getDistance()));

//    autonomousShootCommand = new AutonomousShootCommand(new MoveTurretByAngle(yawControl, () ->
//        vision.getOuterTarget().getHorizontalOffset()), new ShootAndCount(new SpinShooterAndLoaderByDistance(shooter,
//        loaderConveyor, () -> vision.getOuterTarget().getDistance()),
//        ballCollector, shooter),
//        new DriveByDistance(driveTrain, () -> 0.5));
//
//    final JoystickButton alignToTargetButton =
//        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
//    alignToTargetButton.whenActive(autonomousShootCommand);
  }
  @Override
  public void autonomousInit() {
//    if(autonomousShootCommand != null) autonomousShootCommand.schedule();
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
