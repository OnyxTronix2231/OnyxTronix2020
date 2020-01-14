package robot.conveyance.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.conveyance.StorageConveyor;

import java.util.function.DoubleSupplier;

public class MoveStorageConveyorBySpeed extends CommandBase {
  private final StorageConveyor storageConveyor;
  private final DoubleSupplier speedSupplier;

  public MoveStorageConveyorBySpeed(final StorageConveyor storageConveyor, final DoubleSupplier speedSupplier) {
    this.storageConveyor = storageConveyor;
    this.speedSupplier = speedSupplier;
    addRequirements(storageConveyor);
  }

  @Override
  public void initialize() {
    storageConveyor.moveConveyorBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    storageConveyor.stopMotorStorageConveyor();
  }
}
