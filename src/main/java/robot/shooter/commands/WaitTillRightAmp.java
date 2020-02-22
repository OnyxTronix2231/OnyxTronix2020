package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

public class WaitTillRightAmp extends CommandBase {

  private final Shooter shooter;
  private boolean reachedVelocity;

  public WaitTillRightAmp(final Shooter shooter){
    this.shooter = shooter;
  }

  @Override
  public void initialize() {
    reachedVelocity = false;
  }

  @Override
  public boolean isFinished() {
    if(!reachedVelocity && shooter.isOnTarget()) {
      reachedVelocity = true;
    }
    boolean retVal = shooter.isBallShot() && reachedVelocity;
    if(retVal) {
      reachedVelocity = false;
    }
    return retVal;
  }
}
