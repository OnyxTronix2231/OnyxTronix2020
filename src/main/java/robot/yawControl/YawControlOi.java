package robot.yawControl;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.drivetrain.DriveTrain;
import robot.vision.target.ConditionalTargetMaker;
import robot.vision.target.VisionTargetFactory;
import robot.yawControl.commands.AlignByVisionOrOdometryAndVision;
import robot.yawControl.commands.SetTurretState;

public class YawControlOi {
  public YawControlOi(final YawControl yawControl, final DriveTrain driveTrain,
                      final ConditionalTargetMaker targetMaker, final UniqueButtonCache buttonJoystickButtonCache,
                      final UniqueAxisCache buttonsJoystickAxisCache) {
    final JoystickButton alignByToTargetButton =
        buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kY.value);
    alignByToTargetButton.whenPressed(new AlignByVisionOrOdometryAndVision(yawControl, driveTrain, targetMaker::makeTarget));

    final JoystickButton setStateRTFButton = buttonJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    setStateRTFButton.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.RTF));

    final JoystickButton setStateRTRButton = buttonJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);
    setStateRTRButton.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.RTR));

    final JoystickButton setStateHOMINGButton = buttonJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kA.value);
    setStateHOMINGButton.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.HOMING));
  }
}

