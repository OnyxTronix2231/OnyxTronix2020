package robot.roulette.commands;

import robot.roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinRouletteToColorPeriodically extends SpinRouletteToColor {

    public SpinRouletteToColorPeriodically(final Roulette roulette, DoubleSupplier supplierColorsRequired) {
        super(roulette, supplierColorsRequired);
        addRequirements(roulette);
    }

    @Override
    public void execute() {
        colorsRequired = supplierColorsRequired.getAsDouble();
        super.execute();
    }
}
