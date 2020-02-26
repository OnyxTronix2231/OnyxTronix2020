package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.commands.MoveTurretByAngleAndKeep;
import robot.vision.target.VisionTarget;
import robot.yawControl.YawControl;

import java.util.function.Supplier;

public class AlignByVisionOrOrientationAndVision extends IsTargetFoundCondition {

  public AlignByVisionOrOrientationAndVision(final YawControl yawControl, final DriveTrain driveTrain,
                                             final Supplier<VisionTarget> visionTargetSupplier) {
//    super(new AlignByVisionUntilTargetNotFound(yawControl, () -> visionTargetSupplier.get().getHorizontalOffset()),
//        new AlignByFieldOrientation(yawControl));
    super(new MoveTurretByAngleAndKeep(yawControl, () -> visionTargetSupplier.get().getHorizontalOffset()),
        new AlignByFieldOrientationUntilTargetFound(yawControl));
  }

  @Override
  public boolean isFinished() {
    if(super.isFinished()) {
      initialize();
    }
    return false;
  }
}
