package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.shooter.Shooter;

public class CloseShooterPiston extends InstantCommand {

  private final Shooter shooter;

  public CloseShooterPiston(final Shooter shooter) {
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    shooter.closeShooterPiston();
  }
}

