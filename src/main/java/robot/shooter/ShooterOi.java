package robot.shooter;

import static robot.shooter.ShooterConstants.PERCENT_OUT_PUT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import robot.ballCollector.BallCollector;
import robot.shooter.commands.ShootAndCount;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootCondition;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                   UniqueButtonCache buttonsJoystickButtonCache, final Shooter shooter,
                   final BallCollector ballCollector) {
    final JoystickButton shootBySpeedButton =
        buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
    shootBySpeedButton.whileActiveContinuous(new ShootCondition(new ShootAndCount(shooter, ballCollector,
        () -> PERCENT_OUT_PUT), ballCollector));

    final Trigger openPiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    openPiston.whileActiveOnce(new OpenShooterPiston(shooter));

    final Trigger closePiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickRight.value);
    closePiston.whileActiveOnce(new CloseShooterPiston(shooter));

  }
}
