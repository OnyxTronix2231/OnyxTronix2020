package robot.ballCollector.commands;

import static robot.ballCollector.BallCollectorConstants.*;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballCollector.BallCollector;

public class ClosePistonsWhileMovingBallCollector extends InstantCommand {

  private final BallCollector ballCollector;

  public ClosePistonsWhileMovingBallCollector(BallCollector ballCollector){
    this.ballCollector = ballCollector;
  }

  @Override
  public void execute() {
    new WaitCommand(COLLECT_AND_CLOSE_TIMER);
    ballCollector.collectBySpeed(PERCENT_OUTPUT);
    ballCollector.closePistons();
  }

  @Override
  public void end(final boolean interrupted) {
    ballCollector.stopMotor();
  }
}
