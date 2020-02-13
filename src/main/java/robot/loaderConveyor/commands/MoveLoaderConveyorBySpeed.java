package robot.loaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.loaderConveyor.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class MoveLoaderConveyorBySpeed extends CommandBase {

  private final LoaderConveyor loaderConveyor;
  private final DoubleSupplier speedSupplier;

  public MoveLoaderConveyorBySpeed(final LoaderConveyor loaderConveyor,
                                   final DoubleSupplier speedSupplier) {
    this.loaderConveyor = loaderConveyor;
    this.speedSupplier = speedSupplier;
  }

  @Override
  public void execute() {
    loaderConveyor.moveLoaderConveyorBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    loaderConveyor.stopMotor();
  }
}
