package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.shooter.Shooter;

public class OpenSolenoid extends InstantCommand {

  private final Shooter shooter;

  public OpenSolenoid(final Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    shooter.openSolenoid();
  }
}
