package robot.conveyance.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.conveyance.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class MoveLoaderConveyorBySpeed extends CommandBase {
  private final LoaderConveyor loaderConveyor;
  private final DoubleSupplier speedSupplier;

  public MoveLoaderConveyorBySpeed(final LoaderConveyor loaderConveyor, final DoubleSupplier speedSupplier) {
    this.loaderConveyor = loaderConveyor;
    this.speedSupplier = speedSupplier;
    addRequirements(loaderConveyor);
  }

  @Override
  public void initialize() {
    loaderConveyor.moveConveyorBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    loaderConveyor.stopMotorStorageConveyor();
  }
}
