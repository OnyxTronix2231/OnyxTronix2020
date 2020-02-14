package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.roulette.Roulette;

public class CloseRoulettePistons extends InstantCommand {

    private final Roulette roulette;

    public CloseRoulettePistons(final Roulette roulette) {
        this.roulette = roulette;
        addRequirements(roulette);
    }

    @Override
    public void initialize() {
        roulette.closeDoubleSolenoid();
    }
}
