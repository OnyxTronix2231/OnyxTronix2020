package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;

import java.util.function.Supplier;

public class CollectBallBySpeed extends CommandBase {

    private final BallCollector ballCollector;
    private final Supplier<Double> speedSupplier;

    public CollectBallBySpeed(final BallCollector ballCollector, Supplier<Double> speedSupplier) {
        this.ballCollector = ballCollector;
        this.speedSupplier = speedSupplier;
        addRequirements(ballCollector);
    }

    @Override
    public void execute() {
        ballCollector.moveBySpeed(speedSupplier.get());
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
