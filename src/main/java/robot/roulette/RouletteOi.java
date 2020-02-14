package robot.roulette;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.roulette.commands.CloseRoulettePistons;
import robot.roulette.commands.SpinRouletteByColorCount;
import robot.roulette.commands.SpinRouletteToColorIfExists;

public class RouletteOi {

    public RouletteOi(final Roulette roulette, final UniqueButtonCache buttonsJoystickButtonCache) {

        final JoystickButton togglePistons = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
        togglePistons.whenActive(new CloseRoulettePistons(roulette));

        final JoystickButton spinRouletteByColorExist = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
        spinRouletteByColorExist.whenActive(new SpinRouletteToColorIfExists(roulette));

        final JoystickButton spinRouletteByColorCount = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
        spinRouletteByColorCount.whenPressed(new SpinRouletteByColorCount(roulette, () -> 8));
    }
}
