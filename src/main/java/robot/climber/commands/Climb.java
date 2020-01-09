package robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.climber.Climber;

import java.util.function.DoubleSupplier;

public class Climb extends CommandBase {

    private final Climber climber;
    private final DoubleSupplier speed;

    public Climb(final Climber climber, DoubleSupplier speed) {
        this.climber = climber;
        this.speed = speed;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.moveBySpeed(speed.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopMotor();
    }
}
