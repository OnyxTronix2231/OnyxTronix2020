package robot.yawControl;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.drivetrain.DriveTrain;
import robot.vision.target.VisionTarget;
import robot.vision.target.VisionTargetSupplier;
import robot.yawControl.commands.AlignByVisionOrOrientationAndVision;
import robot.yawControl.commands.SetTurretState;

import java.util.function.Supplier;

public class YawControlOi {
  public YawControlOi(final YawControl yawControl, final DriveTrain driveTrain,
                      final VisionTargetSupplier targetSupplier, final UniqueButtonCache buttonJoystickButtonCache,
                      final UniqueButtonCache driverJoystickButtonCache) {
    final JoystickButton alignToTargetDriveJoystick =
        driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    final JoystickButton alignToTargetButtonJoystick = buttonJoystickButtonCache.createJoystickTrigger(
        XboxController.Button.kA.value
    );
    alignToTargetDriveJoystick.or(alignToTargetButtonJoystick).whenActive(new
        AlignByVisionOrOrientationAndVision(yawControl, targetSupplier));

    final JoystickButton setStateRTFButton = buttonJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBack.value);
    setStateRTFButton.whenActive(new SetTurretState(yawControl, YawControl.TurretState.RTF));

    final JoystickButton setStateRTRButton = buttonJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStart.value);
    setStateRTRButton.whenActive(new SetTurretState(yawControl, YawControl.TurretState.RTR));

    final JoystickButton setStateHOMINGButton = buttonJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStickRight.value);
    setStateHOMINGButton.whenActive(new SetTurretState(yawControl, YawControl.TurretState.HOMING));
  }
}

