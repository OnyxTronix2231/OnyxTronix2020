package robot.ballCounter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCounter.BallCounter;
import robot.shooter.Shooter;

import java.util.function.BooleanSupplier;

public class CountShotBall extends CommandBase {

  private final BallCounter ballCounter;
  private final Shooter shooter;
  private final BooleanSupplier isVelocityErrorSufficient;
  private int timer;
  private boolean isBallBeingShot;

  public CountShotBall(final BallCounter ballCounter, final Shooter shooter, final BooleanSupplier isVelocityErrorSufficient) {
    this.ballCounter = ballCounter;
    this.shooter = shooter;
    this.isVelocityErrorSufficient = isVelocityErrorSufficient;
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    if (isVelocityErrorSufficient.getAsBoolean()) {
      timer++;
      if (timer >= 7) {
        isBallBeingShot = true;
      }
    }else if (isBallBeingShot) {
      isBallBeingShot = false;
      ballCounter.removeBall();
    }
  }

  @Override
  public boolean isFinished() {
    return ballCounter.canShoot();
  }
}
