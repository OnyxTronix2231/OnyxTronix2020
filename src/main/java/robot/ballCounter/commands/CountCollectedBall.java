package robot.ballCounter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCounter.BallCounter;

import java.util.function.BooleanSupplier;

public class CountCollectedBall extends CommandBase {

  private final BallCounter ballCounter;
  private final BooleanSupplier isAmpSufficient;
  private boolean isBallCollectingOrShooting;

  public CountCollectedBall(final BallCounter ballCounter, final BooleanSupplier booleanSupplier) {
    this.ballCounter = ballCounter;
    this.isAmpSufficient = booleanSupplier;
  }

  @Override
  public void execute() {
    if (isAmpSufficient.getAsBoolean()) {
      isBallCollectingOrShooting = true;
    }else if (isBallCollectingOrShooting) {
      isBallCollectingOrShooting = false;
        ballCounter.removeBall();
    }
  }

  @Override
  public boolean isFinished() {
      return !ballCounter.canCollect();
  }
}
