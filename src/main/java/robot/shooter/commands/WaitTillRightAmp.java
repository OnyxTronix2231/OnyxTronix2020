package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

public class WaitTillRightAmp extends CommandBase {

  private final Shooter shooter;

  public WaitTillRightAmp(final Shooter shooter){
    this.shooter = shooter;
  }

  @Override
  public boolean isFinished() {
    return shooter.isBallShot();
  }
}
