package robot.conveyance.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.TransportToShooter.TransportToShooter;
import robot.conveyance.Conveyance;

import java.util.function.DoubleSupplier;

public class MoveBallConveyanceBySpeed extends CommandBase {
    private final Conveyance conveyance;
    private final DoubleSupplier speedSupplier;

    public MoveBallConveyanceBySpeed(Conveyance conveyance, DoubleSupplier speed) {
        this.conveyance = conveyance;
        this.speedSupplier = speed;
    }

    @Override
    public void initialize() {
        conveyance.moveConveyanceBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        conveyance.stopMotorFirstConveyance();
    }
}
