package robot.roulette;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.roulette.commands.*;

import static edu.wpi.first.wpilibj.XboxController.Button.*;
import static onyxTronix.JoystickAxis.AxisMap.kRightX;

public class RouletteOi {

    public RouletteOi(final Roulette roulette, final UniqueAxisCache driverJoystickAxisCache,
                      final UniqueButtonCache driverJoystickButtonCache){

        final JoystickAxis rouletteBySpeedAxis =
                driverJoystickAxisCache.createJoystickTrigger(kRightX.value);
        rouletteBySpeedAxis.whileActiveContinuous(new SpinRouletteBySpeed(roulette, rouletteBySpeedAxis::getRawAxis));

        final Trigger closeDoubleSolenoid = driverJoystickButtonCache.createJoystickTrigger(kA.value);
        closeDoubleSolenoid.whenActive(new CloseDoubleSolenoid(roulette));

        final Trigger openDoubleSolenoid = driverJoystickButtonCache.createJoystickTrigger(kX.value);
        openDoubleSolenoid.whenActive(new OpenDoubleSolenoid(roulette));

        final Trigger spinRouletteByColorExist = driverJoystickButtonCache.createJoystickTrigger(kB.value);
        spinRouletteByColorExist.whenActive(new SpinRouletteByColorIfExists(roulette));

        final Trigger spinRouletteToColor = driverJoystickButtonCache.createJoystickTrigger(kY.value);
        spinRouletteToColor.whenActive(new SpinRouletteToColor(roulette, roulette::getRequiredColorFromMatchColor));


    }
}
