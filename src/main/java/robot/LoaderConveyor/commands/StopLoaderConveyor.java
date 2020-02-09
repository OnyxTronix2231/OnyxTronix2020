package robot.LoaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.LoaderConveyor.LoaderConveyor;

public class StopLoaderConveyor extends InstantCommand {
  private final LoaderConveyor loaderConveyor;

  public StopLoaderConveyor(LoaderConveyor loaderConveyor) {
    this.loaderConveyor = loaderConveyor;
  }

  @Override
  public void end(boolean interrupted) {
    loaderConveyor.stopMotor();
  }
}
