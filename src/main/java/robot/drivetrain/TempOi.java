package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.Paths.TEST_PATH;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.drivetrain.commands.MoveByPath;

public class TempOi {
  public TempOi(final UniqueButtonCache driverJoystickButtonCache, final DriveTrain driveTrain) {
    final Trigger testTrigger = driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
    testTrigger.whenActive(new MoveByPath(driveTrain, TEST_PATH()[0]).andThen(
        new MoveByPath(driveTrain, TEST_PATH()[1])
    ));
  }
}
