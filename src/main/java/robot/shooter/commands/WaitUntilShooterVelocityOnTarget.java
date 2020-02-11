package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class WaitUntilShooterVelocityOnTarget extends CommandBase {
    private final Shooter shooter;
    private final DoubleSupplier velocitySupplier;

    public WaitUntilShooterVelocityOnTarget(final Shooter shooter, DoubleSupplier velocitySupplier){
        this.shooter = shooter;
        this.velocitySupplier = velocitySupplier;
    }

    @Override
    public boolean isFinished() {
        return shooter.isOnTarget();
    }
}
