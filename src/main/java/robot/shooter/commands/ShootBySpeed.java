package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

public class ShootBySpeed extends CommandBase {
    private final double speed;
    private final Shooter shooter;
    public ShootBySpeed(final Shooter shooter, final double speed){
        this.shooter = shooter;
        this.speed = speed;

    }

    @Override
    public void execute() {
        shooter.moveBySpeed(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
