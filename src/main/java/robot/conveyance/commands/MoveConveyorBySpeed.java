package robot.conveyance.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.conveyance.Conveyor;

import java.util.function.DoubleSupplier;

public class MoveConveyorBySpeed extends CommandBase {
    private final Conveyor conveyance;
    private final DoubleSupplier speedSupplier;

    public MoveConveyorBySpeed( final Conveyor conveyance,final DoubleSupplier speedSupplier) {
        this.conveyance = conveyance;
        this.speedSupplier = speedSupplier;
    }

    @Override
    public void execute() {
        conveyance.moveConveyorBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(final boolean interrupted) {
        conveyance.stopMotor();
    }
}
