package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;

public class Collect extends CommandBase {

    private final BallCollector ballCollector;

    public Collect(final BallCollector ballCollector) {
        this.ballCollector = ballCollector;
        addRequirements(ballCollector);
    }

    @Override
    public void execute() {
        ballCollector.moveBySpeed(0.5);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        ballCollector.moveBySpeed(0);
    }
}
