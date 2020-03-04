package robot.ballCounter;

import static robot.ballCounter.BallCounterConstants.MAX_AMOUNT_OF_BALLS;
import static robot.ballCounter.BallCounterConstants.MIN_AMOUNT_OF_BALLS;
import static robot.ballCounter.BallCounterConstants.STARTING_AMOUNT_OF_BALLS;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class BallCounter {

  private int ballCounter;

  public BallCounter() {
    ballCounter = STARTING_AMOUNT_OF_BALLS;
    Shuffleboard.getTab("BallCounter").addNumber("ballCounter", () -> ballCounter);
  }

  public void ballCollected() {
    ballCounter++;
  }

  public void ballShot() {
    ballCounter--;
  }

  public boolean canCollect() {
    return ballCounter < MAX_AMOUNT_OF_BALLS;
  }

  public boolean canShoot() {
    return ballCounter > MIN_AMOUNT_OF_BALLS;
  }
}
