package robot.storageConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.storageConveyor.StorageConveyor;

import java.util.function.DoubleSupplier;

public class MoveStorageConveyorBySpeed extends CommandBase {
    private final StorageConveyor storageConveyance;
    private final DoubleSupplier speedSupplier;

    public MoveStorageConveyorBySpeed(final StorageConveyor storageConveyance, final DoubleSupplier speedSupplier) {
        this.storageConveyance = storageConveyance;
        this.speedSupplier = speedSupplier;
    }

    @Override
    public void execute() {
        storageConveyance.moveBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(final boolean interrupted) {
        storageConveyance.stopMotor();
    }
}