package robot.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.climber.Climber;

public class Climb extends CommandBase {

    private final Climber climber;

    public Climb(final Climber climber){
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        climber.moveBySpeed(0.5);
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
