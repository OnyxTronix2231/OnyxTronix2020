package robot.roulette;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class OpenDoubleSolenoid extends CommandBase {
    private final Roulette roulette;

    public OpenDoubleSolenoid(final Roulette roulette) {
        addRequirements(roulette);
        this.roulette = roulette;
    }

    @Override
    public void initialize() {
        roulette.openDoubleSolenoid();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
