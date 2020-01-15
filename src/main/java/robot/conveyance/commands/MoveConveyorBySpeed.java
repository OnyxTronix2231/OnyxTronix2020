package robot.conveyance.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.conveyance.Conveyor;

import java.util.function.DoubleSupplier;

public class MoveConveyorBySpeed extends CommandBase {
    private final Conveyor conveyance;
    private final DoubleSupplier speedSupplier;

    public MoveConveyorBySpeed( final Conveyor conveyance,final DoubleSupplier speed) {
        this.conveyance = conveyance;
        this.speedSupplier = speed;
    }

    @Override
    public void initialize() {
        conveyance.moveConveyanceBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(final boolean interrupted) {
        conveyance.stopMotorFirstConveyance();
    }
}
