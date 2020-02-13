package robot.roulette.commands;

import robot.roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinRouletteToColorPeriodically extends SpinRouletteToColor {

    public SpinRouletteToColorPeriodically(final Roulette roulette, DoubleSupplier colorsRequiredSupplier) {
        super(roulette, colorsRequiredSupplier);
        addRequirements(roulette);
    }

    @Override
    public void execute() {
        colorsRequired = colorsRequiredSupplier.getAsDouble();
        super.execute();
    }
}
