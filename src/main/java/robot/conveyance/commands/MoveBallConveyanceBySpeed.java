package robot.conveyance.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.conveyance.StorageConveyor;

import java.util.function.DoubleSupplier;

public class MoveBallConveyanceBySpeed extends CommandBase {
  private final StorageConveyor storageConveyance;
  private final DoubleSupplier speedSupplier;

  public MoveBallConveyanceBySpeed(final StorageConveyor storageConveyance, final DoubleSupplier speedSupplier) {
    this.storageConveyance = storageConveyance;
    this.speedSupplier = speedSupplier;
    addRequirements(storageConveyance);
  }

  @Override
  public void initialize() {
    storageConveyance.moveConveyorBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    storageConveyance.stopMotorStorageConveyor();
  }
}
