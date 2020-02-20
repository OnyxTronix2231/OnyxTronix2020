package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.commands.MoveTurretToAngle;
import robot.vision.target.VisionTarget;
import robot.yawControl.YawControl;

import java.util.function.Supplier;

public class AlignByVisionOrOdometryAndVision extends IsTargetFoundCondition {

  public AlignByVisionOrOdometryAndVision(final YawControl yawControl, final DriveTrain driveTrain,
                                          final Supplier<VisionTarget> visionTargetSupplier) {
    super(new MoveTurretToAngle(yawControl, () -> visionTargetSupplier.get().getHorizontalOffset()),
        new AlignByOdometryAndVision(yawControl, driveTrain, visionTargetSupplier));
  }
}
