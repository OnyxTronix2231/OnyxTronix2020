package robot;

import static robot.RobotConstants.DRIVE_JOYSTICK_PORT;
import static robot.RobotConstants.OPERATOR_JOYSTICK_PORT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.BallCollector;
import robot.ballCollector.BallCollectorOiBinder;
import robot.ballStopper.BallStopper;
import robot.ballTrigger.BallTriggerOiBinder;
import robot.climber.Climber;
import robot.climber.ClimberOiBinder;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainOiBinder;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.ShooterOiBinder;
import robot.storageConveyor.StorageConveyor;
import robot.turret.TurretOiBinder;
import robot.vision.target.VisionTargetSupplier;
import robot.yawControl.YawControl;
import robot.yawControl.YawControlOiBinder;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class Oi {
  public Oi(DriveTrain driveTrain, Shooter shooter, YawControl yawControl, Climber climber, BallCollector ballCollector,
            LoaderConveyor loaderConveyor, StorageConveyor storageConveyor, BallStopper ballStopper,
            DoubleSupplier shootingDistanceSupplier, VisionTargetSupplier targetSupplier,
            BooleanSupplier canReleaseBallSupplier, BooleanSupplier canReleaseBallAtCloseRangeSupplier) {

    final XboxController driverJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
    final UniqueButtonCache driverJoystickButtonCache = new UniqueButtonCache(driverJoystick);
    final UniqueAxisCache driverJoystickAxisCache = new UniqueAxisCache(driverJoystick);

    final XboxController operatorJoystick = new XboxController(OPERATOR_JOYSTICK_PORT);
    final UniqueButtonCache operatorJoystickButtonCache = new UniqueButtonCache(operatorJoystick);
    final UniqueAxisCache operatorJoystickAxisCache = new UniqueAxisCache(operatorJoystick);

    //region DriveTrain
    JoystickAxis driverForwardAxis = driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);
    JoystickAxis driverRotateAxis = driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);

    new DriveTrainOiBinder(driveTrain, driverForwardAxis,
        driverRotateAxis);
    //endregion

    //region Shooter
    final Trigger driveSpinShooterWhileAligning = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    final Trigger operatorSpinShooterWhileAligning = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kA.value);
    final Trigger driverAndOperatorSpinShooterAndAlign =
        driveSpinShooterWhileAligning.or(operatorSpinShooterWhileAligning);

    final JoystickAxis driverAlignAndShootBall = driverJoystickAxisCache
        .createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    final Trigger driverShootBallAtCloseRange = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    new ShooterOiBinder(shooter, shootingDistanceSupplier,
        driverAndOperatorSpinShooterAndAlign, driverAlignAndShootBall,
        driverShootBallAtCloseRange);
    //endregion

    //region Turret
    final JoystickAxis operatorMoveTurretByAxis =
        operatorJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightX.value);

    new TurretOiBinder(yawControl, operatorMoveTurretByAxis);
    //endregion

    //region YawControl
    final Trigger driverSetStateRTF = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBack.value);

    final Trigger driverSetStateRTR = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStart.value);

    final Trigger driverSetStateHoming = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStickRight.value);

    new YawControlOiBinder(yawControl, targetSupplier,
        driverSetStateRTF, driverSetStateRTR,
        driverSetStateHoming.or(driverShootBallAtCloseRange),
        driverAndOperatorSpinShooterAndAlign.or(driverAlignAndShootBall));
    //endregion

    //region Climber
    final Trigger driverOpenClimber = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStart.value);

    final Trigger driverClimb = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBack.value);

    new ClimberOiBinder(climber, driverOpenClimber, driverClimb);
    //endregion

    //region BallCollector
    final Trigger driverOpenAndCollectBall = driverJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    final Trigger operatorOpenAndCollectBall = operatorJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    final Trigger openAndCollectBall = driverOpenAndCollectBall.or(operatorOpenAndCollectBall);

    final Trigger operatorUncollectBalls = operatorJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    final Trigger operatorOpenThenCloseCollector = operatorJoystickButtonCache.
        createJoystickTrigger(XboxController.Button.kBumperLeft.value);

    new BallCollectorOiBinder(ballCollector, openAndCollectBall,
        operatorUncollectBalls, operatorOpenThenCloseCollector);
    //endregion

    //region BallTrigger
    final Trigger triggerBallManually = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kB.value);

    final Trigger moveConveyorsReverse = driverJoystickButtonCache.
        createJoystickTrigger(XboxController.Button.kX.value);

    new BallTriggerOiBinder(loaderConveyor, storageConveyor,
        ballStopper, canReleaseBallSupplier,
        canReleaseBallAtCloseRangeSupplier,
        openAndCollectBall, driverAlignAndShootBall,
        driverShootBallAtCloseRange, triggerBallManually,
        moveConveyorsReverse);
    //endregion
  }
}
