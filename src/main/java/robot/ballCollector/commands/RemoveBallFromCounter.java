package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;

public class RemoveBallFromCounter extends InstantCommand {

  private  final BallCollector ballCollector;

  public RemoveBallFromCounter(final BallCollector ballCollector){
    this.ballCollector = ballCollector;
  }

  @Override
  public void initialize() {
    ballCollector.removeBall();
  }
}
