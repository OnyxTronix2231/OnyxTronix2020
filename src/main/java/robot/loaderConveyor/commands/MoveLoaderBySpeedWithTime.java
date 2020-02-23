package robot.loaderConveyor.commands;

import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.TIME_TO_MOVE_LOADER;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;

import java.util.function.DoubleSupplier;
public class MoveLoaderBySpeedWithTime extends WaitCommand {

  private final LoaderConveyor loaderConveyor;
  private final DoubleSupplier speedSupplier;

  public MoveLoaderBySpeedWithTime(final LoaderConveyor loaderConveyor,
                                   final DoubleSupplier speedSupplier,
                                   final double delayTime) {
    super(delayTime);
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
