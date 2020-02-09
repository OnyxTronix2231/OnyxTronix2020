package robot.LoaderConveyor.commands;

import robot.LoaderConveyor.LoaderConveyor;
import java.util.function.DoubleSupplier;

public class MoveLoaderByVelocity extends MoveLoaderByVelocityWhitoutEnd {

  private final LoaderConveyor loaderConveyor;

  public MoveLoaderByVelocity(final LoaderConveyor loaderConveyor, final DoubleSupplier velocitySupplier) {
    super(loaderConveyor, velocitySupplier);
    this.loaderConveyor =  loaderConveyor;
  }

  @Override
  public void end(final boolean interrupted) {
    loaderConveyor.stopMotor();
  }
}
