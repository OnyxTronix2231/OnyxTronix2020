package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.drivetrain.DriveTrain;
import robot.turret.commands.MoveTurretByAngle;
import robot.turret.commands.MoveTurretToAngle;
import robot.vision.target.VisionTarget;
import robot.yawControl.YawControl;

import java.util.function.Supplier;

public class AlignByOdometryAndVision extends SequentialCommandGroup {

  public AlignByOdometryAndVision(final YawControl yawControl, final DriveTrain driveTrain,
                                  final Supplier<VisionTarget> visionTargetSupplier) {
    addCommands(
        new MoveTurretToFieldTargetByPose(yawControl, driveTrain),
        new IsTargetFoundCondition(new MoveTurretByAngle(yawControl, () -> visionTargetSupplier.get().getHorizontalOffset()),
            new InstantCommand())
    );
  }
}
