package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByVelocity extends CommandBase {

    private final Shooter shooter;
    private final DoubleSupplier velocity;

    public ShootByVelocity(final Shooter shooter, final DoubleSupplier velocity){
        this.shooter = shooter;
        this.velocity = velocity;
    }

    @Override
    public void execute() {
        shooter.setVelocity(velocity.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopMotor();
    }
}
