package robot.loaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.loaderConveyor.LoaderConveyor;

public class WaitUntilLoaderVelocityOnTarget extends WaitCommand {
  private final LoaderConveyor loaderConveyor;

  public WaitUntilLoaderVelocityOnTarget(final LoaderConveyor loaderConveyor, final double delayTime) {
    super(delayTime);
    this.loaderConveyor = loaderConveyor;
  }

  @Override
  public boolean isFinished() {
    return loaderConveyor.isOnTarget() && super.isFinished();
  }
}
