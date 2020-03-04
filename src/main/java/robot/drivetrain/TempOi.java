package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.Paths.PRIMARY_PATH;
import static robot.drivetrain.DriveTrainConstants.Paths.TEST_PATH;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.drivetrain.commands.MoveByPath;
import robot.drivetrain.commands.MoveToPose;

public class TempOi {
  public TempOi(final UniqueButtonCache driverJoystickButtonCache, final DriveTrain driveTrain) {
    final Trigger testTrigger = driverJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
    testTrigger
//        .whenActive(new MoveToPose(driveTrain, new Pose2d(1,0, Rotation2d.fromDegrees(0))));
        .whenActive(new MoveByPath(driveTrain, PRIMARY_PATH()[0])
        .andThen(
        new MoveByPath(driveTrain, PRIMARY_PATH()[1])).andThen(
                new MoveByPath(driveTrain, PRIMARY_PATH()[2]))
                .andThen(
                    new MoveByPath(driveTrain, PRIMARY_PATH()[3]))

    );
  }
}
