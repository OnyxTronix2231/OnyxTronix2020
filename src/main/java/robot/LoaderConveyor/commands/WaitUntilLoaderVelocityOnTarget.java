package robot.LoaderConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.LoaderConveyor.LoaderConveyor;

import java.util.function.DoubleSupplier;

public class WaitUntilLoaderVelocityOnTarget extends CommandBase {
    private final LoaderConveyor loaderConveyor;
    private final DoubleSupplier velocitySupplier;

    public WaitUntilLoaderVelocityOnTarget(final LoaderConveyor loaderConveyor, DoubleSupplier velocitySupplier){
        this.loaderConveyor = loaderConveyor;
        this.velocitySupplier = velocitySupplier;
    }

    @Override
    public boolean isFinished() {
        return loaderConveyor.getVelocity()>=velocitySupplier.getAsDouble();
    }
}
