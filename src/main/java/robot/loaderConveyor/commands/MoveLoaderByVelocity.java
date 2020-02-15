package robot.loaderConveyor.commands;

import robot.loaderConveyor.LoaderConveyor;
import java.util.function.DoubleSupplier;

public class MoveLoaderByVelocity extends MoveLoaderByVelocityWithoutEnd {

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
