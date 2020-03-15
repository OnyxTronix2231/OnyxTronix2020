package robot.autonomous;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.autonomous.commands.TestWeirdPath;
import robot.drivetrain.DriveTrain;

public class AutonomousOiBinder {

  public AutonomousOiBinder(final DriveTrain driveTrain, final Trigger testAutonomous) {
    testAutonomous.whileActiveOnce(new TestWeirdPath(driveTrain));
  }
}

