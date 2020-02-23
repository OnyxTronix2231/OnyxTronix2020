package robot.loaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.loaderConveyor.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class MoveLoaderConveyorBySpeed extends WaitCommand {

  private final LoaderConveyor loaderConveyor;
  private final DoubleSupplier speedSupplier;

  public MoveLoaderConveyorBySpeed(final LoaderConveyor loaderConveyor,
                                   final DoubleSupplier speedSupplier) {
    super(0.8);
    this.loaderConveyor = loaderConveyor;
    this.speedSupplier = speedSupplier;
    addRequirements(loaderConveyor);
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
