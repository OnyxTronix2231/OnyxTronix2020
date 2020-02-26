package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.drivetrain.DriveTrain;
import robot.turret.commands.MoveTurretByAngleAndKeep;
import robot.vision.target.VisionTarget;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;

import java.util.function.Supplier;

public class AlignByVisionOrOrientationAndVision extends SequentialCommandGroup {

  public AlignByVisionOrOrientationAndVision(final YawControl yawControl, final DriveTrain driveTrain,
                                             final Supplier<VisionTarget> visionTargetSupplier) {
//    super(new AlignByVisionUntilTargetNotFound(yawControl, () -> visionTargetSupplier.get().getHorizontalOffset()),
//        new AlignByFieldOrientation(yawControl));
    super(new AlignByFieldOrientationUntilTargetFound(yawControl),
        new WaitUntilCommand(() -> Limelight.getInstance().targetFound()),
        new MoveTurretByAngleAndKeep(yawControl, () -> visionTargetSupplier.get().getHorizontalOffset()).alongWith(new
            RunCommand(() -> Limelight.getInstance().setTakeSnapshots(true))));
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    Limelight.getInstance().setTakeSnapshots(false);
  }

  @Override
  public boolean isFinished() {
    if(super.isFinished()) {
      initialize();
    }
    return false;
  }
}
