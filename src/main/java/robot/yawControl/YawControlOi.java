package robot.yawControl;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.drivetrain.DriveTrain;
import robot.vision.target.ConditionalTargetMaker;
import robot.yawControl.commands.AlignByVisionOrOdometryAndVision;
import robot.yawControl.commands.SetTurretState;

public class YawControlOi {
  public YawControlOi(final YawControl yawControl, final DriveTrain driveTrain,
                      final ConditionalTargetMaker targetMaker, final UniqueButtonCache buttonJoystickButtonCache,
                      final UniqueAxisCache buttonsJoystickAxisCache,
                      final UniqueButtonCache driverJoystickButtonCache) {
    final JoystickButton alignByToTargetButton =
        driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kY.value);
    alignByToTargetButton.whenPressed(new AlignByVisionOrOdometryAndVision(yawControl, driveTrain, targetMaker::makeTarget));

    final JoystickAxis setStateRTFButton = buttonsJoystickAxisCache
        .createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    setStateRTFButton.whenActive(new SetTurretState(yawControl, YawControl.TurretState.RTF));

    final JoystickAxis setStateRTRButton = buttonsJoystickAxisCache
        .createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    setStateRTRButton.whenActive(new SetTurretState(yawControl, YawControl.TurretState.RTR));

    final JoystickButton setStateHOMINGButton = buttonJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kStickRight.value);
    setStateHOMINGButton.whenActive(new SetTurretState(yawControl, YawControl.TurretState.HOMING));
  }
}

