package robot.loaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.loaderConveyor.LoaderConveyor;

public class StopLoaderConveyor extends InstantCommand {
  private final LoaderConveyor loaderConveyor;

  public StopLoaderConveyor(final LoaderConveyor loaderConveyor) {
    this.loaderConveyor = loaderConveyor;
  }

  @Override
  public void initialize() {
    loaderConveyor.stopMotor();
  }
}
