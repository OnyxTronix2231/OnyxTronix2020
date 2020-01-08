package robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.climber.Climber;

import java.util.function.Supplier;

public class Climb extends CommandBase {

    private final Climber climber;
    private final Supplier<Double> speedSupplier;
    public Climb(final Climber climber, Supplier<Double> speedSupplier){
        this.climber = climber;
        this.speedSupplier = speedSupplier;
        addRequirements(climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        climber.moveBySpeed(speedSupplier.get());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        climber.moveBySpeed(0);
    }
}
