package robot.roulette.commands;

import robot.roulette.Roulette;
import robot.roulette.RouletteColor;

import java.util.function.DoubleSupplier;

public class SpinRouletteByColorCount extends SpinRouletteToColor {

    private RouletteColor currentColor;
    private RouletteColor previousColor;

    public SpinRouletteByColorCount(final Roulette roulette, DoubleSupplier colorsRequiredSupplier){
        super(roulette, colorsRequiredSupplier);
        addRequirements(roulette);
    }

    @Override
    public void initialize() {
        previousColor = roulette.getCurrentColor();
        super.initialize();
    }

    @Override
    public void execute() {
        currentColor = roulette.getCurrentColor();
        if (currentColor.compareTo(previousColor) == 0) {
            previousColor = currentColor;
            colorsRequired = Math.copySign(Math.abs(colorsRequired) - 1, colorsRequired);
        }
        super.execute();
    }
}
