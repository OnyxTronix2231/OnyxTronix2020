package robot.shooter;

import static robot.shooter.ShooterConstants.PERCENT_OUT_PUT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.ballCollector.BallCollector;
import robot.shooter.commands.ShootAndCount;
import robot.shooter.commands.ShootBySpeed;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;


public class ShooterOi {

  public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                   UniqueButtonCache buttonsJoystickButtonCache, final Shooter shooter,
                   final ShootAndCount shootAndCount, final BallCollector ballCollector) {
    final JoystickAxis shootBySpeedAxis =
        buttonJoystickAxisCache.createJoystickTrigger(XboxController.Button.kX.value);
    shootBySpeedAxis.whileActiveContinuous(new ShootCondition(shootAndCount, ballCollector));

    final Trigger openPiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    openPiston.whileActiveOnce(new OpenShooterPiston(shooter));

    final Trigger closePiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickRight.value);
    closePiston.whileActiveOnce(new CloseShooterPiston(shooter));

  }
}
