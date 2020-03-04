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
  public Oi(final DriveTrain driveTrain, final Shooter shooter, final YawControl yawControl, final Climber climber,
            final BallCollector ballCollector, final LoaderConveyor loaderConveyor,
            final StorageConveyor storageConveyor, final BallStopper ballStopper,
            final DoubleSupplier shootingDistanceSupplier, final VisionTargetSupplier targetSupplier,
            final BooleanSupplier canReleaseBallSupplier, final BooleanSupplier canReleaseBallAtCloseRangeSupplier) {

    final XboxController driverJoystick = new XboxController(DRIVE_JOYSTICK_PORT);
    final UniqueButtonCache driverJoystickButtonCache = new UniqueButtonCache(driverJoystick);
    final UniqueAxisCache driverJoystickAxisCache = new UniqueAxisCache(driverJoystick);

    final XboxController operatorJoystick = new XboxController(OPERATOR_JOYSTICK_PORT);
    final UniqueButtonCache operatorJoystickButtonCache = new UniqueButtonCache(operatorJoystick);
    final UniqueAxisCache operatorJoystickAxisCache = new UniqueAxisCache(operatorJoystick);

    //region DriveTrain
    JoystickAxis driverKLeftYForwardAxis = driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);
    JoystickAxis driverKLeftYRotateAxis = driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);

    new DriveTrainOiBinder(driveTrain, driverKLeftYForwardAxis,
        driverKLeftYRotateAxis);
    //endregion

    //region Shooter
    final Trigger driveKBumperLeftSpinShooterWhileAligning = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    final Trigger operatorKASpinShooterWhileAligning = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kA.value);
    final Trigger driverAndOperatorSpinShooterAndAlign =
        driveKBumperLeftSpinShooterWhileAligning.or(operatorKASpinShooterWhileAligning);

    final JoystickAxis driverKRightTriggerAlignAndShootBall = driverJoystickAxisCache
        .createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    final Trigger driverKBumperRightShootBallAtCloseRange = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    new ShooterOiBinder(shooter, shootingDistanceSupplier,
        driverAndOperatorSpinShooterAndAlign, driverKRightTriggerAlignAndShootBall,
        driverKBumperRightShootBallAtCloseRange);
    //endregion

    //region Turret
    final JoystickAxis operatorKRightXMoveTurretByAxis =
        operatorJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightX.value);

    new TurretOiBinder(yawControl, operatorKRightXMoveTurretByAxis);
    //endregion

    //region YawControl
    final Trigger operatorKBackSetStateRTF = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBack.value);

    final Trigger operatorKStartSetStateRTR = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStart.value);

    final Trigger operatorKStickRightSetStateHoming = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStickRight.value);

    new YawControlOiBinder(yawControl, targetSupplier,
        operatorKBackSetStateRTF, operatorKStartSetStateRTR,
        operatorKStickRightSetStateHoming.or(driverKBumperRightShootBallAtCloseRange),
        driverAndOperatorSpinShooterAndAlign.or(driverKRightTriggerAlignAndShootBall));
    //endregion

    //region Climber
    final Trigger driverKStartOpenClimber = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStart.value);

    final Trigger driverKBackClimb = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBack.value);

    new ClimberOiBinder(climber, driverKStartOpenClimber, driverKBackClimb);
    //endregion

    //region BallCollector
    final Trigger driverKLeftTriggerOpenAndCollectBall = driverJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    final Trigger operatorKLeftTriggerOpenAndCollectBall = operatorJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    final Trigger openAndCollectBall = driverKLeftTriggerOpenAndCollectBall.or(operatorKLeftTriggerOpenAndCollectBall);

    final Trigger operatorKRightTriggerUncollectBalls = operatorJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    final Trigger operatorKBumperLeftOpenThenCloseCollector = operatorJoystickButtonCache.
        createJoystickTrigger(XboxController.Button.kBumperLeft.value);

    new BallCollectorOiBinder(ballCollector, openAndCollectBall,
        operatorKRightTriggerUncollectBalls, operatorKBumperLeftOpenThenCloseCollector);
    //endregion

    //region BallTrigger
    final Trigger driverKBReleaseBallManually = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kB.value);

    final Trigger driverKXMoveConveyorsReverse = driverJoystickButtonCache.
        createJoystickTrigger(XboxController.Button.kX.value);

    new BallTriggerOiBinder(loaderConveyor, storageConveyor,
        ballStopper, canReleaseBallSupplier,
        canReleaseBallAtCloseRangeSupplier,
        openAndCollectBall, driverKRightTriggerAlignAndShootBall,
        driverKBumperRightShootBallAtCloseRange, driverKBReleaseBallManually,
        driverKXMoveConveyorsReverse);
    //endregion
  }
}
