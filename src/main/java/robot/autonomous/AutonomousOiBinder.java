package robot.autonomous;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.autonomous.commands.ResetPosition;
import robot.autonomous.commands.TestSPath;
import robot.autonomous.commands.TestWeirdPath;
import robot.drivetrain.DriveTrain;

public class AutonomousOiBinder {

  public AutonomousOiBinder(final DriveTrain driveTrain, final Trigger testAutonomous, final Trigger resetPosition) {
    testAutonomous.whileActiveOnce(new TestSPath(driveTrain));
    resetPosition.whenActive(new ResetPosition(driveTrain));
  }
}

