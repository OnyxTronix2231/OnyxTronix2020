package robot;

import static robot.RobotConstants.ROBOT_TYPE;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import robot.autonomous.commands.DriveThenShootAutonomous;
import robot.ballCollector.BallCollector;
import robot.ballCollector.BallCollectorComponents;
import robot.ballCollector.BasicBallCollectorComponentsA;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperComponents;
import robot.ballStopper.BasicBallStopperComponentsA;
import robot.climber.BasicClimberComponentsA;
import robot.climber.Climber;
import robot.climber.ClimberComponents;
import robot.drivetrain.BasicDriveTrainComponentsA;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainComponents;
import robot.loaderConveyor.BasicLoaderConveyorComponentsA;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorComponents;
import robot.shooter.BasicShooterComponentsA;
import robot.shooter.Shooter;
import robot.shooter.ShooterComponents;
import robot.storageConveyor.BasicStorageConveyorComponentsA;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorComponents;
import robot.turret.BasicTurretComponentsA;
import robot.turret.TurretComponents;
import robot.vision.Vision;
import robot.vision.VisionConstants;
import robot.vision.target.VisionTargetFactory;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;
import vision.limelight.enums.LimelightLedMode;

public class Robot extends TimedRobot {

  private DriveThenShootAutonomous autonomousShooting;

  Vision vision;
  DriveTrain driveTrain;
  Climber climber;

  @Override
  public void robotInit() {
    LiveWindow.disableAllTelemetry();

    final DriveTrainComponents driveTrainComponents;
    final BallCollectorComponents ballCollectorComponents;
    final BallStopperComponents ballStopperComponents;
    final StorageConveyorComponents storageConveyorComponents;
    final TurretComponents turretComponents;
    final LoaderConveyorComponents loaderConveyorComponents;
    final ShooterComponents shooterComponents;
    final ClimberComponents climberComponents;

    if (ROBOT_TYPE == RobotType.A) {
      driveTrainComponents = new BasicDriveTrainComponentsA();
      ballCollectorComponents = new BasicBallCollectorComponentsA();
      ballStopperComponents = new BasicBallStopperComponentsA();
      storageConveyorComponents = new BasicStorageConveyorComponentsA();
      turretComponents = new BasicTurretComponentsA();
      loaderConveyorComponents = new BasicLoaderConveyorComponentsA();
      shooterComponents = new BasicShooterComponentsA();
      climberComponents = new BasicClimberComponentsA();
    } else {
      driveTrainComponents = null; //TODO: use BasicDriveTrainComponentsB Here
      ballCollectorComponents = null; //TODO: use BasicBallCollectorComponentsB Here
      ballStopperComponents = null; //TODO: use BasicBallStopperComponentsB Here
      storageConveyorComponents = null; //TODO: use BasicStorageConveyorComponentsB Here
      turretComponents = null;  //TODO: use BasicTurretComponentsB Here
      loaderConveyorComponents = null; //TODO: use BasicLoaderConveyorComponentsB Here
      shooterComponents = null; //TODO: use BasicShooterComponentsB Here
      climberComponents = null; // TODO: use BasicClimberComponentsB Here
    }

    driveTrain = new DriveTrain(driveTrainComponents);

    final BallStopper ballStopper = new BallStopper(ballStopperComponents);

    final StorageConveyor storageConveyor = new StorageConveyor(storageConveyorComponents);

    final YawControl yawControl = new YawControl(turretComponents, driveTrain);

    final LoaderConveyor loaderConveyor = new LoaderConveyor(loaderConveyorComponents);

    final Shooter shooter = new Shooter(shooterComponents);

    final BallCollector ballCollector = new BallCollector(ballCollectorComponents);

    climber = new Climber(climberComponents);

    vision = new Vision(new VisionTargetFactory(yawControl::getAngleRTR,
        driveTrain::getOdometryHeading,
        VisionConstants.RobotAConstants.CAMERA_VERTICAL_OFFSET_ANGLE,
        VisionConstants.RobotAConstants.CAMERA_HEIGHT_CM, Limelight.getInstance()));

    new Oi(driveTrain, shooter, yawControl, climber, ballCollector, loaderConveyor, storageConveyor, ballStopper,
        () -> vision.getOuterTarget().getDistance(), vision::getDependableTarget,
        () -> canReleaseBall(shooter, yawControl),
        () -> canReleaseBallAtCloseRange(shooter, yawControl));

    autonomousShooting = new DriveThenShootAutonomous(yawControl, driveTrain, shooter,
        loaderConveyor, storageConveyor, ballStopper, vision, () -> canReleaseBall(shooter, yawControl));

    Shuffleboard.getTab("Shooter").addNumber("Velocity by distance",
        () -> shooter.distanceToVelocity(vision.getDependableTarget().getDistance()));
    Shuffleboard.getTab("Drive").addBoolean("Shooter On Target Velocity", shooter::isOnTarget);
    Shuffleboard.getTab("Drive").addBoolean("Can Hit Inner", () ->
        vision.canHitOuterTarget());
    Shuffleboard.getTab("Drive").add("Starting angle", 180).
        getEntry().addListener(v -> driveTrain.setGyroAngle(v.value.getDouble()), EntryListenerFlags.kUpdate);
    Shuffleboard.getTab("Drive").addBoolean("Vision Target Found", () -> Limelight.getInstance().targetFound());
  }

  @Override
  public void autonomousInit() {
    vision.setLEDMode(LimelightLedMode.forceOn);
    driveTrain.setNeutralModeToBrake();
    climber.setNeutralModeToBrake();
    autonomousShooting.schedule();
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    vision.setLEDMode(LimelightLedMode.forceOn);
    driveTrain.setNeutralModeToBrake();
    climber.setNeutralModeToBrake();
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
    vision.setLEDMode(LimelightLedMode.forceOff);
    driveTrain.setNeutralModeToCoast();
    climber.setNeutralModeToCoast();
  }

  @Override
  public void disabledPeriodic() {
    CommandScheduler.getInstance().run();
  }

  private boolean canReleaseBall(Shooter shooter, YawControl yawControl) {
    return shooter.isOnTarget() && Limelight.getInstance().targetFound() && yawControl.isOnTarget();
  }

  private boolean canReleaseBallAtCloseRange(Shooter shooter, YawControl yawControl) {
    return shooter.isOnTarget() && yawControl.isOnTarget();
  }
}
