package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.turret.commands.AlignByVision;
import robot.vision.target.VisionTargetSupplier;
import robot.yawControl.YawControl;

public class AlignByOrientationAndThenVision extends SequentialCommandGroup {

  public AlignByOrientationAndThenVision(final YawControl yawControl,
                                         final VisionTargetSupplier visionTargetSupplier) {
    super(
        new AlignByFieldOrientation(yawControl), new AlignByVision(yawControl, visionTargetSupplier),
        new AlignByVision(yawControl, visionTargetSupplier));
  }

  @Override
  public boolean isFinished() {
    if (super.isFinished()) {
      initialize();
    }
    return false;
  }
}
