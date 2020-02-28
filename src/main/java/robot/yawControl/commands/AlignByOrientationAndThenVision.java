package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.turret.commands.AlignByVision;
import robot.turret.commands.MoveTurretByAngleAndKeep;
import robot.vision.target.VisionTargetSupplier;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;

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

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    Limelight.getInstance().setTakeSnapshots(false);
  }
}
