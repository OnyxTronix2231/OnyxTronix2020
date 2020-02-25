package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;
import robot.loaderConveyor.LoaderConveyor;

public class WaitUntilBallInLoader extends CommandBase {
  private final LoaderConveyor loaderConveyor;

  public WaitUntilBallInLoader(LoaderConveyor loaderConveyor) {
    this.loaderConveyor = loaderConveyor;
  }

  @Override
  public boolean isFinished() {
    return loaderConveyor.isBallInLoader();
  }
}
