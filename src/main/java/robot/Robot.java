package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;
import static robot.RobotConstants.ROBOT_TYPE;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.autonomous.PrimaryPath;
import robot.autonomous.SecondaryPath;
import robot.ballCollector.BallCollector;
import robot.ballCollector.BallCollectorComponents;
import robot.ballCollector.TestingBallCollectorOi;
import robot.ballCollector.BasicBallCollectorComponentsA;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperComponents;
import robot.ballStopper.TestingBallStopperOi;
import robot.ballStopper.BasicBallStopperComponentsA;
import robot.crossSubsystem.SmartShooterOi;
import robot.drivetrain.BasicDriveTrainComponentsA;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainComponents;
import robot.drivetrain.OnyxTrajectoryGenerator;
import robot.drivetrain.commands.DriveByJoystick;
import robot.loaderConveyor.BasicLoaderConveyorComponentsA;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorComponents;
import robot.loaderConveyor.TestingLoaderConveyorOi;
import robot.shooter.BasicShooterComponentsA;
import robot.shooter.Shooter;
import robot.shooter.ShooterComponents;
import robot.shooter.TestingShooterOi;
import robot.storageConveyor.BasicStorageConveyorComponentsA;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorComponents;
import robot.storageConveyor.TestingStorageConveyorOi;
import robot.turret.BasicTurretComponentsA;
import robot.turret.TurretComponents;
import robot.turret.TestingTurretOi;
import robot.vision.Vision;
import robot.vision.target.VisionTargetFactory;
import robot.yawControl.YawControl;
import robot.yawControl.YawControlOi;
import vision.limelight.Limelight;

public class Robot extends TimedRobot {

  private SendableChooser<Integer> pathChooser = new SendableChooser<>();
  private DriveTrain driveTrain;
  private BallCollector ballCollector;
  private BallStopper ballStopper;
  private StorageConveyor storageConveyor;
  private YawControl yawControl;
  private LoaderConveyor loaderConveyor;
  private Shooter shooter;

  @Override
  public void robotInit() {
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

    driveTrain = new DriveTrain(driveTrainComponents);
    driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));

    ballCollector = new BallCollector(ballCollectorComponents);
    new TestingBallCollectorOi(ballCollector, driveJoystickAxisCache, buttonsJoystickAxisCache, driveJoystickButtonCache);

    ballStopper = new BallStopper(ballStopperComponents);
    new TestingBallStopperOi(ballStopper, buttonsJoystickButtonCache);

    storageConveyor = new StorageConveyor(storageConveyorComponents);
    new TestingStorageConveyorOi(storageConveyor, driveJoystickButtonCache);

    yawControl = new YawControl(turretComponents, driveTrain);
    new TestingTurretOi(yawControl, buttonsJoystickAxisCache);

    loaderConveyor = new LoaderConveyor(loaderConveyorComponents);
    new TestingLoaderConveyorOi(loaderConveyor, buttonsJoystickButtonCache);

    shooter = new Shooter(shooterComponents);
    new TestingShooterOi(buttonsJoystickAxisCache, driveJoystickButtonCache, shooter);

    Vision vision = new Vision(new VisionTargetFactory(driveTrain::getOdometryHeading,
        yawControl::getTurretAngleRTF, 30.0, 25, Limelight.getInstance()));

    new SmartShooterOi(driveJoystickButtonCache, driveJoystickAxisCache, shooter, loaderConveyor,
        storageConveyor, ballStopper, vision);

    new YawControlOi(yawControl, driveTrain, vision::getOuterTarget, buttonsJoystickButtonCache, driveJoystickAxisCache);

    pathChooser.setDefaultOption("Path 1", 1);
    pathChooser.addOption("Path 2", 2);
    pathChooser.addOption("Path 3", 3);
    pathChooser.addOption("Path 4", 4);
    pathChooser.addOption("Path 5", 5);
    Shuffleboard.enableActuatorWidgets();

    Shuffleboard.getTab("Autonomous").add("ComboBox", pathChooser).withWidget(BuiltInWidgets.kComboBoxChooser);
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    final OnyxTrajectoryGenerator trajectoryGenerator = new OnyxTrajectoryGenerator();
    switch (pathChooser.getSelected()) {
      case 1:
        new PrimaryPath(shooter, driveTrain, trajectoryGenerator, ballCollector).schedule();
        break;
      case 2:
        new SecondaryPath().schedule();
        break;
    }
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
