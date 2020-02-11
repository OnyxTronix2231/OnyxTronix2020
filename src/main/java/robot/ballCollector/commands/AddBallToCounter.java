package robot.ballCollector.commands;

import static robot.ballCollector.BallCollectorConstants.MIN_AMP_FOR_ONE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballCollector.BallCollector;

public class AddBallToCounter extends InstantCommand {

  private final BallCollector ballCollector;

  public AddBallToCounter(final BallCollector ballCollector){
    this.ballCollector = ballCollector;
  }

  @Override
  public void initialize() {
    ballCollector.addBall();
  }
}
