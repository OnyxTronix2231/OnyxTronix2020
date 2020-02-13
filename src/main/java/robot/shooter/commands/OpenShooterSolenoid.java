package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.shooter.Shooter;

public class OpenShooterSolenoid extends InstantCommand {

  private final Shooter shooter;

  public OpenShooterSolenoid(final Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    shooter.openShooterSolenoid();
  }
}
