package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;

public class OpenPistons extends InstantCommand {

  private final BallCollector ballCollector;


  public OpenPistons(final BallCollector ballCollector) {
    this.ballCollector = ballCollector;
    addRequirements(ballCollector);
  }

  @Override
  public void initialize() {
    ballCollector.openPistons();
  }
}
