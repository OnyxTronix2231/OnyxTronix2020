package robot.LoaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
