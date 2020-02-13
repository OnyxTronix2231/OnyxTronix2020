package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.shooter.commands.ShootBySpeed;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.CloseSolenoid;
import robot.shooter.commands.OpenSolenoid;
import static robot.shooter.ShooterConstants.PERECENT_OUT_PUT;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                   UniqueButtonCache buttonsJoystickButtonCache, final Shooter shooter) {
    final JoystickAxis shootBySpeedAxis =
        buttonJoystickAxisCache.createJoystickTrigger(XboxController.Button.kX.value);
    shootBySpeedAxis.whileActiveContinuous(new ShootBySpeed(shooter, () -> PERECENT_OUT_PUT));

    final Trigger openSolenoid = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    openSolenoid.whileActiveOnce(new OpenSolenoid(shooter)); //TODO check before merge

    final Trigger closeSolenoid = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickRight.value);
    closeSolenoid.whileActiveOnce(new CloseSolenoid(shooter)); //TODO check before merge

  }
}
