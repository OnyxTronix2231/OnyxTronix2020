package robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.autonomous.AutonomousConstants;
import robot.drivetrain.DriveTrain;

public class ResetPosition extends InstantCommand {
  private final DriveTrain driveTrain;

  public ResetPosition(final DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
  }

  @Override
  public void initialize() {
    driveTrain.resetRobotToPose(AutonomousConstants.STARTING_POSE);
  }
}
