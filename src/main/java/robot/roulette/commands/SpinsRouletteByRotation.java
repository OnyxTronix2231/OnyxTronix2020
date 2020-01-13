package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.RGBValue;
import robot.roulette.Roulette;
import robot.roulette.RouletteColor;
import robot.roulette.RouletteConstants;

public class SpinsRouletteByRotation extends CommandBase {

    private final Roulette roulette;
    private int counter;
    private RouletteColor initialColor;
    private RouletteColor currentColor;
    private RouletteColor lastColor;

    public SpinsRouletteByRotation(final Roulette roulette) {
        this.roulette = roulette;
    }

    @Override
    public void initialize() {
        initialColor = roulette.getClosestColor();
        counter = 0;
    }

    @Override
    public void execute() {
        roulette.spinMotor(RouletteConstants.SPIN_ROTATIONS_SPEED);
        currentColor = roulette.getClosestColor();

        if(!currentColor.getRgbValue().equals(initialColor)){
            return;
        }else{
            if(currentColor.getRgbValue().equals(lastColor)){
                return;
            }else{
                lastColor = currentColor;
                counter++;
            }
        }
    }

    @Override
    public boolean isFinished() {
        return RouletteConstants.REQUIRED_ROTATIONS <= counter;
    }

    @Override
    public void end(boolean interrupted) {
        roulette.stopSpin();
    }
}
