package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class CollectBallBySpeed extends CommandBase {

    private final BallCollector ballCollector;
    private final DoubleSupplier speedSupplier;

    public CollectBallBySpeed(final BallCollector ballCollector,final DoubleSupplier speedSupplier) {
        this.ballCollector = ballCollector;
        this.speedSupplier = speedSupplier;
        addRequirements(ballCollector);
    }

    @Override
    public void execute() {
        ballCollector.moveBySpeed(speedSupplier.getAsDouble());
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
