package robot.loaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.loaderConveyor.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class MoveLoaderByVelocityWithoutEnd extends CommandBase {

  private final LoaderConveyor loaderConveyor;
  private final DoubleSupplier velocitySupplier;

  public MoveLoaderByVelocityWithoutEnd(final LoaderConveyor loaderConveyor, final DoubleSupplier velocitySupplier) {
    this.loaderConveyor = loaderConveyor;
    this.velocitySupplier = velocitySupplier;
    addRequirements(loaderConveyor);
  }

  @Override
  public void execute() {
    loaderConveyor.setVelocity(velocitySupplier.getAsDouble());
  }

}
