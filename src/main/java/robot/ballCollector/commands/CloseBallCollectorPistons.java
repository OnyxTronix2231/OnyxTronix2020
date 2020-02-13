package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;

public class CloseBallCollectorPistons extends InstantCommand {

  private final BallCollector ballCollector;

  public CloseBallCollectorPistons(final BallCollector ballCollector) {
    this.ballCollector = ballCollector;
    addRequirements(ballCollector);
  }

  @Override
  public void initialize() {
    ballCollector.closePistons();
  }
}
