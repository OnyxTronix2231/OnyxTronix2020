package robot.ballCounter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCounter.BallCounter;

import java.util.function.BooleanSupplier;

public class CountCollectedBall extends CommandBase {

  private final BallCounter ballCounter;
  private final BooleanSupplier booleanSupplier;
  private boolean isBallCollecting;
  private final boolean isCollecting;
  private int delayCounter;

  public CountCollectedBall(final BallCounter ballCounter, final BooleanSupplier booleanSupplier,
                            final boolean collectingNotShooting) {
    this.ballCounter = ballCounter;
    this.booleanSupplier = booleanSupplier;
    this.isCollecting = collectingNotShooting;
    delayCounter = 0;
  }

  @Override
  public void execute() {
    if (booleanSupplier.getAsBoolean()){
      delayCounter++;
      if (delayCounter <= 3){
        isBallCollecting = true;
        delayCounter = 0;
      }
    }else if (isBallCollecting){
      isBallCollecting = false;
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
