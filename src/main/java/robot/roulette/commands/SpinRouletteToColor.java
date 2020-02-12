package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinRouletteToColor extends CommandBase {
    protected final Roulette roulette;
    protected DoubleSupplier supplierColorsRequired;
    protected double colorsRequired;

    public SpinRouletteToColor(final Roulette roulette, DoubleSupplier supplierColorsRequired) {
        this.roulette = roulette;
        this.supplierColorsRequired = supplierColorsRequired;
        addRequirements(roulette);
    }

    @Override
    public void initialize() {
        colorsRequired = supplierColorsRequired.getAsDouble();
        roulette.resetEncoder();
    }

    @Override
    public void execute() {
        roulette.spinByColorsCount(colorsRequired);
    }

    @Override
    public boolean isFinished() {
        return colorsRequired == 0;
    }

    @Override
    public void end(final boolean interrupted) {
        roulette.stopSpin();
    }
}
