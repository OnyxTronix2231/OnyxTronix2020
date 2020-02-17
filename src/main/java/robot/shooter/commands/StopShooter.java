package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.shooter.Shooter;

public class StopShooter extends InstantCommand {

  private Shooter shooter;

    public StopShooter(final Shooter shooter){
      this.shooter = shooter;
    }

  @Override
  public void end(final boolean interrupted) {
    shooter.stopMotor();
  }
}
