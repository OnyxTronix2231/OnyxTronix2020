package robot.yawControl;

import static robot.RobotConstants.ALIGNING_TIME_OUT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.drivetrain.DriveTrain;
import robot.vision.target.VisionTarget;
import robot.yawControl.commands.AlignByVisionOrOrientationAndVision;
import robot.yawControl.commands.SetTurretState;

import java.util.function.Supplier;

public class YawControlOi {
  public YawControlOi(final YawControl yawControl, final DriveTrain driveTrain,
                      final Supplier<VisionTarget> targetSupplier, final UniqueButtonCache buttonJoystickButtonCache,
                      final UniqueButtonCache driverJoystickButtonCache) {
    final JoystickButton alignToTargetButton =
        driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    alignToTargetButton.whenActive(new AlignByVisionOrOrientationAndVision(yawControl, driveTrain, targetSupplier)
        .withTimeout(ALIGNING_TIME_OUT));

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

