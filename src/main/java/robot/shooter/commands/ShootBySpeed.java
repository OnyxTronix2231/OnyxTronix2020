package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;
import java.util.function.DoubleSupplier;

public class ShootBySpeed extends CommandBase {

    private final DoubleSupplier speed;
    private final Shooter shooter;

    public ShootBySpeed(final Shooter shooter, final DoubleSupplier speed) {
        this.shooter = shooter;
        this.speed = speed;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.shootBySpeed(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopMotor();
    }
}
