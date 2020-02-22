package robot.ballStopper.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballStopper.BallStopper;

public class StopBallStopper extends InstantCommand {

  private final BallStopper ballStopper;

  public StopBallStopper(BallStopper ballStopper){
    this.ballStopper = ballStopper;
  }

  @Override
  public void initialize() {
    ballStopper.moveBallStopperMotor(0);
    ballStopper.moveBallStopperDelayMotor(0);
  }
}
