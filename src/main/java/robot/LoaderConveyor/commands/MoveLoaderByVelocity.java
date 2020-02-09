package robot.LoaderConveyor.commands;

import robot.LoaderConveyor.LoaderConveyor;
import java.util.function.DoubleSupplier;

public class MoveLoaderByVelocity extends MoveLoaderByVelocityWhitoutEnd {

  private final LoaderConveyor loaderConveyor;

  public MoveLoaderByVelocity(LoaderConveyor loaderConveyor, DoubleSupplier velocitySupplier) {
    super(loaderConveyor, velocitySupplier);
    this.loaderConveyor =  loaderConveyor;
  }

  @Override
    public void end(final boolean interrupted) {
      loaderConveyor.stopMotor();
    }
  }
