package robot.ballCounter.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.ballCounter.BallCounter;
import robot.shooter.Shooter;
import robot.shooter.commands.IsVelocityErrorSufficient;

public class WaitTillVelocityErrorThenCount extends SequentialCommandGroup {
  public WaitTillVelocityErrorThenCount(final Shooter shooter, final BallCounter ballCounter) {
    addCommands(new IsVelocityErrorSufficient(shooter), new WaitUntilCommand(shooter::isBallNotShot),
        new RemoveBallFromBallCounter(ballCounter));
  }

  @Override
  public void initialize() {
  }

  @Override
  public boolean isFinished() {
    initialize();
    return false;
  }
}
