package robot.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.shooter.commands.ShootBySpeed;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootByVelocity;

import static robot.shooter.ShooterConstants.PERCENT_OUT_PUT;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                   UniqueButtonCache buttonsJoystickButtonCache, final Shooter shooter) {
    final Trigger shootBySpeedButton =
        buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
    final JoystickAxis shootBySpeedJoystick = buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    shootBySpeedJoystick.whileActiveContinuous(new ShootBySpeed(shooter, shootBySpeedJoystick::getRawAxis));
    final Trigger openPiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    openPiston.whileActiveOnce(new OpenShooterPiston(shooter));

    final Trigger closePiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickRight.value);
    closePiston.whileActiveOnce(new CloseShooterPiston(shooter));

  }
}
