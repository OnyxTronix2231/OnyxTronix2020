package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinRouletteBySpeed extends CommandBase {

    private final Roulette roulette;
    private final DoubleSupplier speedSupplier;

    public SpinRouletteBySpeed(final Roulette roulette, final DoubleSupplier speedSupplier) {
        this.roulette = roulette;
        this.speedSupplier = speedSupplier;
        addRequirements(roulette);
    }

    @Override
    public void execute() {
        roulette.spinMotor(speedSupplier);
    }

    @Override
    public void end(final boolean interrupted) {
        roulette.stopSpin();
    }
}