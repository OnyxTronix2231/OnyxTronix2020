package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.CloseSolenoid;
import robot.shooter.commands.OpenSolenoid;
import robot.shooter.commands.ShootByPercentOutput;
import static robot.shooter.ShooterConstants.PERECENT_OUT_PUT;

public class ShooterOi {

    public ShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                           UniqueButtonCache buttonsJoystickButtonCache, final Shooter shooter) {
      final Trigger shootBySpeedAxis =
          buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
      shootBySpeedAxis.whileActiveContinuous(new ShootByPercentOutput(shooter,  ()-> PERECENT_OUT_PUT));

      final Trigger openSolenoid = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
      openSolenoid.whileActiveOnce( new OpenSolenoid(shooter)); //TODO check before merge

      final Trigger closeSolenoid = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
      openSolenoid.whileActiveOnce( new CloseSolenoid(shooter)); //TODO check before merge

    }
  }
