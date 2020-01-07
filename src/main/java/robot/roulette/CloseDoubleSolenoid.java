package robot.roulette;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CloseDoubleSolenoid extends CommandBase {
    private final Roulette roulette;

    public CloseDoubleSolenoid(final Roulette roulette) {
        addRequirements(roulette);
        this.roulette = roulette;
    }

    @Override
    public void initialize() {
        roulette.closeDoubleSolenoid();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
