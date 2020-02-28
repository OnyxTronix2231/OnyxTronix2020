package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.shooter.Shooter;

public class WaitUntilShooterVelocityIsNotOnTarget extends CommandBase {
  private final Shooter shooter;

  public WaitUntilShooterVelocityIsNotOnTarget(final Shooter shooter){
    this.shooter = shooter;
  }

  @Override
  public boolean isFinished() {
    return !shooter.isOnTarget();
  }
}
