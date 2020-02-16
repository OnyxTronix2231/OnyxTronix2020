package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class WaitUntilShooterVelocityOnTarget extends CommandBase {
    private final Shooter shooter;

    public WaitUntilShooterVelocityOnTarget(final Shooter shooter){
        this.shooter = shooter;
    }

    @Override
    public boolean isFinished() {
        return shooter.isOnTarget();
    }
}
