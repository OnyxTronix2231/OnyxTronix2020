package robot.loaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.loaderConveyor.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class WaitUntilLoaderVelocityOnTarget extends CommandBase {
  private final LoaderConveyor loaderConveyor;

  public WaitUntilLoaderVelocityOnTarget(final LoaderConveyor loaderConveyor) {
    this.loaderConveyor = loaderConveyor;
  }

  @Override
  public boolean isFinished() {
    return loaderConveyor.isOnTarget();
  }
}
