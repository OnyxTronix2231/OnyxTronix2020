package robot.LoaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.LoaderConveyor.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class MoveLoaderByVelocityWhitoutEnd extends CommandBase {

  private final LoaderConveyor loaderConveyor;
  private final DoubleSupplier velocitySupplier;

  public MoveLoaderByVelocityWhitoutEnd(final LoaderConveyor loaderConveyor, final DoubleSupplier velocitySupplier) {
    this.loaderConveyor = loaderConveyor;
    this.velocitySupplier = velocitySupplier;
    addRequirements(loaderConveyor);
  }

  @Override
  public void execute() {
    loaderConveyor.setVelocity(velocitySupplier.getAsDouble());
  }

}
