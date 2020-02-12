package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;

public class ClosePistons extends InstantCommand {

  private final BallCollector ballCollector;

  public ClosePistons(final BallCollector ballCollector) {
    this.ballCollector = ballCollector;
    addRequirements(ballCollector);
  }

  @Override
  public void initialize() {
    ballCollector.closePistons();
  }
}
