package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;

public class Collect extends CommandBase {

    private final BallCollector ballCollector;

    public Collect(BallCollector ballCollector) {
        this.ballCollector = ballCollector;
    }

    @Override
    public void execute() {
        ballCollector.moveBySpeed(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        ballCollector.moveBySpeed(0);
    }
}
