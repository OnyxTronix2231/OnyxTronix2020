package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.shooter.commands.ShootBySpeed;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import static robot.shooter.ShooterConstants.PERCENT_OUT_PUT;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                   UniqueButtonCache buttonsJoystickButtonCache, final Shooter shooter) {
    final JoystickAxis shootBySpeedAxis =
        buttonJoystickAxisCache.createJoystickTrigger(XboxController.Button.kX.value);
    shootBySpeedAxis.whileActiveContinuous(new ShootBySpeed(shooter, () -> PERCENT_OUT_PUT));

    final Trigger openPiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    openPiston.whileActiveOnce(new OpenShooterPiston(shooter));

    final Trigger closePiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickRight.value);
    closeSolenoid.whileActiveOnce(new CloseShooterPiston(shooter));

  }
}
