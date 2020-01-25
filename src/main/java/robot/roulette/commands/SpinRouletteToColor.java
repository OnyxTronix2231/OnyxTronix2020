package robot.roulette.commands;

import robot.roulette.Roulette;
import robot.roulette.RouletteColor;

import java.util.function.Supplier;

import static robot.roulette.RouletteConstants.COLORS_IN_ROTATIONS;

public class SpinRouletteToColor extends SpinRouletteByColorCount {

    public SpinRouletteToColor(final Roulette roulette, final Supplier<RouletteColor> requiredColor) {
        super(roulette, () -> roulette.getColorCountRequiredToColor(requiredColor.get()));
    }
}
