package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;
import robot.roulette.RouletteColor;

import java.util.function.DoubleSupplier;

public class SpinRouletteByColorCount extends CommandBase {
    private final Roulette roulette;
    private DoubleSupplier colorsRequired;
    private RouletteColor currentColor;
    private RouletteColor previousColor;
    private int colorCounter;

    public SpinRouletteByColorCount(final Roulette roulette, DoubleSupplier colorsRequired ) {
        this.roulette = roulette;
        this.colorsRequired = colorsRequired;
    }

    @Override
    public void initialize() {
        previousColor = roulette.getCurrentColor();
        colorCounter = 0;
    }

    @Override
    public void execute() {
        currentColor = roulette.getCurrentColor();
        roulette.spinByColorsCount(colorsRequired.getAsDouble() - colorCounter);
        if (!currentColor.getRgbValue().equals(previousColor.rgbValue)) {
            colorCounter++;
            previousColor = currentColor;
        }
    }

    @Override
    public boolean isFinished() {
        return colorCounter >= colorsRequired.getAsDouble();
    }

    @Override
    public void end(final boolean interrupted) {
        roulette.stopSpin();
    }
}
