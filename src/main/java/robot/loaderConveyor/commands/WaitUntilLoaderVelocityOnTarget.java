package robot.loaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.loaderConveyor.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class WaitUntilLoaderVelocityOnTarget extends CommandBase {
  private final LoaderConveyor loaderConveyor;
  private final DoubleSupplier velocitySupplier;

  public WaitUntilLoaderVelocityOnTarget(final LoaderConveyor loaderConveyor, final DoubleSupplier velocitySupplier) {
    this.loaderConveyor = loaderConveyor;
    this.velocitySupplier = velocitySupplier;
  }

  @Override
  public boolean isFinished() {
    return loaderConveyor.isOnTarget();
  }
}
