package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.loaderConveyor.LoaderConveyor;

public class WaitUntilBallIsNotInLoader extends CommandBase {
  private final LoaderConveyor loaderConveyor;

  public WaitUntilBallIsNotInLoader(LoaderConveyor loaderConveyor) {
    this.loaderConveyor = loaderConveyor;
  }

  @Override
  public boolean isFinished() {
    return !loaderConveyor.isBallInLoader();
  }
}
