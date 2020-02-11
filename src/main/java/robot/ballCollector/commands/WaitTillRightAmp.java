package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;

public class WaitTillRightAmp extends CommandBase {

  public final BallCollector ballCollector;

  public WaitTillRightAmp(final BallCollector ballCollector){
    this.ballCollector = ballCollector;
  }

  @Override
  public boolean isFinished() {
    return ballCollector.isBallCollected();
  }
}
