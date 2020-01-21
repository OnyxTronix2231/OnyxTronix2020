package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;
import robot.roulette.RouletteColor;
import robot.roulette.RouletteConstants;

import static robot.roulette.RouletteConstants.COLORS_IN_ROTATIONS;
import static robot.roulette.RouletteConstants.MIN_ROTATIONS;

public class SpinRouletteByColor extends CommandBase {
    private final Roulette roulette;
    private int colorsRequired = 8;
    private RouletteColor currentColor;
    private RouletteColor previousColor;
    private int colorCounter;

    public SpinRouletteByColor(final Roulette roulette, int colorsRequired ) {
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
        if (!currentColor.getRgbValue().equals(previousColor.rgbValue)) {
            colorCounter++;
            previousColor = currentColor;
        }
    }
    
    @Override
    public boolean isFinished() {
        return colorCounter >= colorsRequired;
    }

    @Override
    public void end(final boolean interrupted) {
        roulette.stopSpin();
    }
}
