package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import robot.ballCollector.BallCollector;
import robot.loaderConveyor.LoaderConveyor;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;

public class ShooterOi {

  public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                   UniqueButtonCache buttonsJoystickButtonCache, final Shooter shooter,
                   final BallCollector ballCollector, final LoaderConveyor loaderConveyor) {
    final JoystickButton shootBySpeedButton =
        buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
//    shootBySpeedButton.whileActiveContinuous(new ShootCondition(new IsReadyToShoot(
//        new ShootAndCount(new SpinShooterAndLoaderByDistance(shooter, loaderConveyor, ), ballCollector, () -> 1), shooter), ballCollector));

    final Trigger openPiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    openPiston.whileActiveOnce(new OpenShooterPiston(shooter));

    final Trigger closePiston = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickRight.value);
    closePiston.whileActiveOnce(new CloseShooterPiston(shooter));
  }
}
