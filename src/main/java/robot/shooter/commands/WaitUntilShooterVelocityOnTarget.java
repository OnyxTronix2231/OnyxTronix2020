package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.shooter.Shooter;

public class WaitUntilShooterVelocityOnTarget extends WaitCommand {
    private final Shooter shooter;

    public WaitUntilShooterVelocityOnTarget(final Shooter shooter, final double delayTime){
        super(delayTime);
        this.shooter = shooter;
    }

    @Override
    public boolean isFinished() {
        return shooter.isOnTarget() && super.isFinished();
    }
}
