package robot.vision.commands;

import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.RotateByAngleNavX;
import robot.vision.VisionTarget;
import java.util.function.Supplier;

public class CorrectRobotToTarget extends RotateByAngleNavX {

  public CorrectRobotToTarget(final DriveTrain driveTrain, final Supplier<VisionTarget> visionTargetSupplier) {
    super(driveTrain, () -> visionTargetSupplier.get().getHorizontalOffset());
  }
}
