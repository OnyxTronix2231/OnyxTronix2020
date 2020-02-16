package robot.loaderConveyor.commands;

import robot.loaderConveyor.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class MoveLoaderByVelocity extends MoveLoaderByVelocityWithoutEnd {

  public MoveLoaderByVelocity(final LoaderConveyor loaderConveyor, final DoubleSupplier velocitySupplier) {
    super(loaderConveyor, velocitySupplier);
  }

  @Override
  public void end(final boolean interrupted) {
    loaderConveyor.stopMotor();
  }
}
