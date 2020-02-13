package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.roulette.Roulette;

public class OpenDoubleSolenoid extends InstantCommand {

    private final Roulette roulette;

    public OpenDoubleSolenoid(final Roulette roulette) {
        this.roulette = roulette;
        addRequirements(roulette);
    }

    @Override
    public void initialize() {
        roulette.openDoubleSolenoid();
    }
}
