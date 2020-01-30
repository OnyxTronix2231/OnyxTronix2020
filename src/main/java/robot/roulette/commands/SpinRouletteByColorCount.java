package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;
import robot.roulette.RouletteColor;

import java.util.function.DoubleSupplier;

public class SpinRouletteByColorCount extends CommandBase {
    private final Roulette roulette;
    private DoubleSupplier supplierColorsRequired;
    private RouletteColor currentColor;
    private RouletteColor previousColor;
    private int colorCounter;
    private double colorsRequired;

    public SpinRouletteByColorCount(final Roulette roulette, DoubleSupplier supplierColorsRequired) {
        this.roulette = roulette;
        this.supplierColorsRequired = supplierColorsRequired;
        addRequirements(roulette);
    }

    @Override
    public void initialize() {
        colorsRequired = supplierColorsRequired.getAsDouble();
        roulette.resetEncoder();
        previousColor = roulette.getCurrentColor();
        colorCounter = 0;
    }

    @Override
    public void execute() {
        currentColor = roulette.getCurrentColor();
        roulette.spinByColorsCount(
                Math.copySign(Math.abs(colorsRequired) - colorCounter, colorsRequired));
        if (!currentColor.name().equals(previousColor.name())){
                colorCounter++;
            previousColor = currentColor;
        }
        System.out.println(colorCounter);

    }

    private int getColorCounter() {
        return (int) colorsRequired - colorCounter;
    }

    @Override
    public boolean isFinished() {
        return colorCounter >= Math.abs(colorsRequired);
    }

    @Override
    public void end(final boolean interrupted) {
        roulette.stopSpin();
    }
}
