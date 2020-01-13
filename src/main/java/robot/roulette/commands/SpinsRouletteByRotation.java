package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.RGBValue;
import robot.roulette.Roulette;
import robot.roulette.RouletteColor;
import robot.roulette.RouletteConstants;

public class SpinsRouletteByRotation extends CommandBase {
    private final Roulette roulette;
    private int spinsCounter;
    private RouletteColor initialColor;
    private RouletteColor currentColor;
    private RouletteColor previousColor;

    public SpinsRouletteByRotation(final Roulette roulette) {
        this.roulette = roulette;
    }

    @Override
    public void initialize() {
        initialColor = roulette.getClosestColor();
        previousColor = initialColor;
        spinsCounter = 0;
    }

    @Override
    public void execute() {
        roulette.spinMotor(RouletteConstants.SPIN_ROTATIONS_SPEED);
        currentColor = roulette.getClosestColor();
        
        if(currentColor.getRgbValue().equals(initialColor) && !currentColor.getRgbValue().equals(previousColor.getRgbValue())){
	    spinsCounter++;
        }

	previousColor = currentColor;
    }

    @Override
    public boolean isFinished() {
        return RouletteConstants.REQUIRED_ROTATIONS <= spinsCounter;
    }

    @Override
    public void end(boolean interrupted) {
        roulette.stopSpin();
    }
}
