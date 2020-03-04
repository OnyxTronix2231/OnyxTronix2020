package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

public class WaitUntilBallShotByVelocity extends CommandBase {

  private final Shooter shooter;

  public WaitUntilBallShotByVelocity(final Shooter shooter) {
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    shooter.startChecking();
  }

  @Override
  public boolean isFinished() {
    return shooter.isBallShot();
  }
}
