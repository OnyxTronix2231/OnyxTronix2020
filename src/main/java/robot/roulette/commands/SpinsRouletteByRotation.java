package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;
import robot.roulette.RouletteColor;
import robot.roulette.RouletteConstants;

public class SpinsRouletteByRotations extends CommandBase {

    private final Roulette roulette;
    private int counter;
    private RouletteColor currentColor;
    private RouletteColor tempColor;

    public SpinsRouletteByRotations(final Roulette roulette){
        this.roulette = roulette;
    }

    @Override
    public void initialize() {
        currentColor = roulette.getClosestColor();
        counter = 0;
    }

    @Override
    public void execute() {
        roulette.spinMotor(RouletteConstants.SPIN_ROTATIONS_SPEED);
        tempColor = roulette.getClosestColor();
        if (currentColor != tempColor) {
        currentColor = tempColor;
        counter++;
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
