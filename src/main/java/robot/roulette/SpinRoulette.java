package robot.roulette;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinRoulette extends CommandBase {

    private final Roulette roulette;
    private final double speed;

    public SpinRoulette(final Roulette roulette, final double speed) {
        addRequirements(roulette);
        this.roulette = roulette;
        this.speed = speed;
    }

    @Override
    public void execute() {
        roulette.spinMotor(speed);
    }

    @Override
    public void end(final boolean interrupted) {
        roulette.stopSpin();
    }
}
