package robot;

import static robot.RobotConstants.BUTTONS_JOYSTICK_PORT;
import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;
import static robot.RobotConstants.ROBOT_TYPE;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.BallCollector;
import robot.ballCollector.BallCollectorComponents;
import robot.ballCollector.BallCollectorOi;
import robot.ballCollector.BasicBallCollectorComponentsA;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperComponents;
import robot.ballStopper.BallStopperOi;
import robot.ballStopper.BasicBallStopperComponentsA;
import robot.climber.BasicClimberComponentsA;
import robot.climber.Climber;
import robot.climber.ClimberComponents;
import robot.climber.ClimberOi;
import robot.drivetrain.BasicDriveTrainComponentsA;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainComponents;
import robot.drivetrain.commands.DriveByJoystick;
import robot.loaderConveyor.BasicLoaderConveyorComponentsA;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorComponents;
import robot.loaderConveyor.LoaderConveyorOi;
import robot.shooter.BasicShooterComponentsA;
import robot.shooter.Shooter;
import robot.shooter.ShooterComponents;
import robot.shooter.ShooterOi;
import robot.turret.BasicTurretComponentsA;
import robot.turret.Turret;
import robot.turret.TurretComponents;
import robot.turret.TurretOi;

public class Robot extends TimedRobot {

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
    final ClimberComponents climberComponents;
    final LoaderConveyorComponents loaderConveyorComponents;
    final ShooterComponents shooterComponents;
    final TurretComponents turretComponents;

    if (ROBOT_TYPE == RobotType.A) {
      driveTrainComponents = new BasicDriveTrainComponentsA();
      ballCollectorComponents = new BasicBallCollectorComponentsA();
      ballStopperComponents = new BasicBallStopperComponentsA();
      climberComponents = new BasicClimberComponentsA();
      loaderConveyorComponents = new BasicLoaderConveyorComponentsA();
      shooterComponents = new BasicShooterComponentsA();
      turretComponents = new BasicTurretComponentsA();
    } else {
      driveTrainComponents = null; //TODO: use BasicDriveTrainComponentsB Here
      ballCollectorComponents = null; //TODO: use BasicBallCollectorComponentsB Here
      ballStopperComponents = null; //TODO: use BasicBallStopperComponentsB Here
      climberComponents = null; //TODO: use BasicClimberComponentsB Here
      loaderConveyorComponents = null; //TODO: use BasicLoaderConveyorComponentsB Here
      shooterComponents = null; //TODO: use BasicShooterComponentsB Here
      turretComponents = null;  //TODO: use BasicTurretComponentsB Here
    }

    final DriveTrain driveTrain = new DriveTrain(driveTrainComponents);
    driveTrain.setDefaultCommand(new DriveByJoystick(driveTrain, driveJoystick));

    final BallCollector ballCollector = new BallCollector(ballCollectorComponents);
    new BallCollectorOi(ballCollector, driveJoystickAxisCache, driveJoystickButtonCache);

    final BallStopper ballStopper = new BallStopper(ballStopperComponents);
    new BallStopperOi(ballStopper, buttonsJoystickButtonCache);

    final Climber climber = new Climber(climberComponents);
    new ClimberOi(driveJoystickButtonCache, buttonsJoystickAxisCache, climber);

    final LoaderConveyor loaderConveyor = new LoaderConveyor(loaderConveyorComponents);
    new LoaderConveyorOi(loaderConveyor, buttonsJoystickButtonCache);

    final Shooter shooter = new Shooter(shooterComponents);
    new ShooterOi(buttonsJoystickAxisCache, buttonsJoystickButtonCache, shooter);

    final Turret turret = new Turret(turretComponents);
    new TurretOi(turret, buttonsJoystickAxisCache);
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
