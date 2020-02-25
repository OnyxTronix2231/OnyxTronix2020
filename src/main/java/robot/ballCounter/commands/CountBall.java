package robot.ballCounter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCounter.BallCounter;

import java.util.function.BooleanSupplier;

public class CountBall extends CommandBase {

  private final BallCounter ballCounter;
  private final BooleanSupplier booleanSupplier;
  private boolean isBallCollectingOrShooting;
  private final boolean isCollecting;

  public CountBall(final BallCounter ballCounter, final BooleanSupplier booleanSupplier,
                   final boolean collectingNotShooting) {
    this.ballCounter = ballCounter;
    this.booleanSupplier = booleanSupplier;
    this.isCollecting = collectingNotShooting;
  }

  @Override
  public void execute() {
    if (booleanSupplier.getAsBoolean()){
      isBallCollectingOrShooting = true;
    }else if (isBallCollectingOrShooting){
      isBallCollectingOrShooting = false;
      if (isCollecting){
        ballCounter.addBall();
      }else {
        ballCounter.removeBall();
      }
    }
  }

  @Override
  public boolean isFinished() {
    if (isCollecting) {
      return !ballCounter.canCollect();
    }else {
      return !ballCounter.canShoot();
    }
  }
}
