package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.roulette.Roulette;

public class OpenRoulettePistons extends InstantCommand {

    private final Roulette roulette;

    public OpenRoulettePistons(final Roulette roulette) {
        this.roulette = roulette;
        addRequirements(roulette);
    }

    @Override
    public void initialize() {
        roulette.openDoubleSolenoid();
    }
}
