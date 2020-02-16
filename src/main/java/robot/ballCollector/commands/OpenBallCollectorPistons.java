package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;

public class OpenBallCollectorPistons extends InstantCommand {

  private final BallCollector ballCollector;

  public OpenBallCollectorPistons(final BallCollector ballCollector) {
    this.ballCollector = ballCollector;
    addRequirements(ballCollector);
  }

  @Override
  public void initialize() {
    ballCollector.openPistons();
  }
}
