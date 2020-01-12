package robot.conveyance.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.conveyance.StorageConveyor;

import java.util.function.DoubleSupplier;

public class MoveBallConveyanceBySpeed extends CommandBase {
  private final StorageConveyor conveyance;
  private final DoubleSupplier speedSupplier;

  public MoveBallConveyanceBySpeed(StorageConveyor conveyance, DoubleSupplier speed) {
    this.conveyance = conveyance;
    this.speedSupplier = speed;
  }

  @Override
  public void initialize() {
    conveyance.moveConveyorBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    conveyance.stopMotorStorageConveyor();
  }
}
