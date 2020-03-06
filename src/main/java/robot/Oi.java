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

    final JoystickAxis driverKLeftYAxis =
        driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);

    final JoystickAxis driverKRightXAxis =
        driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightX.value);

    //region Driver Triggers
    final Trigger driverKX = driverJoystickButtonCache.
        createJoystickTrigger(XboxController.Button.kX.value);

    final Trigger driverKB = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kB.value);

    final Trigger driveKBumperLeft = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value);

    final Trigger driverKBumperRight = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    final Trigger driverKBack = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBack.value);

    final Trigger driverKStart = driverJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStart.value);

    final Trigger driverKLeftTrigger = driverJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);

    final JoystickAxis driverKRightTrigger = driverJoystickAxisCache
        .createJoystickTrigger(XboxController.Axis.kRightTrigger.value);


    //endregion

    //region Operator Triggers
    final Trigger operatorKA = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kA.value);

    final Trigger operatorKBumperLeft = operatorJoystickButtonCache.
        createJoystickTrigger(XboxController.Button.kBumperLeft.value);

    final Trigger operatorKBack = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBack.value);

    final Trigger operatorKStart = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStart.value);

    final Trigger operatorKStickRight = operatorJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStickRight.value);

    final Trigger operatorKLeftTrigger = operatorJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);

    final Trigger operatorKRightTrigger = operatorJoystickAxisCache.
        createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    final JoystickAxis operatorKRightAxis =
        operatorJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightX.value);
    //endregion

    //region Complex triggers
    final Trigger spinShooterAndAlignToTarget = driveKBumperLeft.or(operatorKA);

    final Trigger makeAShotAtCloseRangeAndMoveTurretToHome = driverKBumperRight;

    final Trigger collectAndLoadBall = driverKLeftTrigger.or(operatorKLeftTrigger);

    final JoystickAxis makeAShotAndAlign = driverKRightTrigger;
    //endregion

    //region DriveTrain
    final JoystickAxis forwardDrive = driverKLeftYAxis;

    final JoystickAxis rotateDrive = driverKRightXAxis;

    new DriveTrainOiBinder(driveTrain, forwardDrive, rotateDrive);
    //endregion

    //region Shooter
    final Trigger spinShooter = spinShooterAndAlignToTarget;

    final JoystickAxis shootBall = makeAShotAndAlign;

    final Trigger shootBallAtCloseRange = makeAShotAtCloseRangeAndMoveTurretToHome;

    new ShooterOiBinder(shooter, shootingDistanceSupplier,
        spinShooter, shootBall,
        shootBallAtCloseRange);
    //endregion

    //region Turret
    final JoystickAxis operatorMoveTurretByAxis = operatorKRightAxis;

    new TurretOiBinder(yawControl, operatorMoveTurretByAxis);
    //endregion

    //region YawControl
    final Trigger setStateRTF = operatorKBack;

    final Trigger setStateRTR = operatorKStart;

    final Trigger setStateHoming = operatorKStickRight.or(makeAShotAtCloseRangeAndMoveTurretToHome);

    final Trigger alignToTarget = spinShooterAndAlignToTarget.or(makeAShotAndAlign);

    new YawControlOiBinder(yawControl, targetSupplier,
        setStateRTF, setStateRTR,
        setStateHoming,
        alignToTarget);
    //endregion

    //region Climber
    final Trigger openClimber = driverKStart;

    final Trigger climb = driverKBack;

    new ClimberOiBinder(climber, openClimber, climb);
    //endregion

    //region BallCollector
    final Trigger openAndCollectBall = collectAndLoadBall;

    final Trigger uncollectBalls = operatorKRightTrigger;

    final Trigger openThenCloseCollector = operatorKBumperLeft;

    new BallCollectorOiBinder(ballCollector, openAndCollectBall,
        uncollectBalls, openThenCloseCollector);
    //endregion

    //region BallTrigger
    final Trigger loadBall = collectAndLoadBall;

    final Trigger releaseBallManually = driverKB;

    final Trigger moveConveyorsReverse = driverKX;

    final Trigger triggerBall = makeAShotAndAlign;

    final Trigger triggerBallAtCloseRange = makeAShotAtCloseRangeAndMoveTurretToHome;

    new BallTriggerOiBinder(loaderConveyor, storageConveyor,
        ballStopper, canReleaseBallSupplier,
        canReleaseBallAtCloseRangeSupplier,
        loadBall, triggerBall,
        triggerBallAtCloseRange, releaseBallManually,
        moveConveyorsReverse);
    //endregion
  }
}
