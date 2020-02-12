package robot.roulette;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.roulette.commands.CloseDoubleSolenoid;
import robot.roulette.commands.OpenDoubleSolenoid;
import robot.roulette.commands.SpinRouletteByColorCount;
import robot.roulette.commands.SpinRouletteBySpeed;
import robot.roulette.commands.SpinRouletteToColorIfExists;

public class RouletteOi {

    public RouletteOi(final Roulette roulette, final UniqueAxisCache driverJoystickAxisCache,
                      final UniqueButtonCache driverJoystickButtonCache) {

        final JoystickAxis rouletteBySpeedAxis =
                driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightX.value);
        rouletteBySpeedAxis.whileActiveContinuous(new SpinRouletteBySpeed(roulette, rouletteBySpeedAxis::getRawAxis));

        final JoystickButton closeDoubleSolenoid = driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
        closeDoubleSolenoid.whenActive(new CloseDoubleSolenoid(roulette));

        final JoystickButton openDoubleSolenoid = driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
        openDoubleSolenoid.whenActive(new OpenDoubleSolenoid(roulette));

        final JoystickButton spinRouletteByColorExist = driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
        spinRouletteByColorExist.whenActive(new SpinRouletteToColorIfExists(roulette));

        final JoystickButton spinRouletteByColorCount = driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperLeft.value);
        spinRouletteByColorCount.whenPressed(new SpinRouletteByColorCount(roulette, () -> 8));
    }
}
