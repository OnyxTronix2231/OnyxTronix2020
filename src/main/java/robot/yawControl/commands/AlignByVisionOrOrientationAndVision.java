package robot.yawControl.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.MIN_CHECKS_VISION;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.crossSubsystem.commands.WaitUntilWithCounter;
import robot.turret.commands.MoveTurretByAngleAndKeep;
import robot.vision.target.VisionTargetSupplier;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;

public class AlignByVisionOrOrientationAndVision extends SequentialCommandGroup {

  public AlignByVisionOrOrientationAndVision(final YawControl yawControl,
                                             final VisionTargetSupplier visionTargetSupplier) {
    super(new AlignByFieldOrientationUntilTargetFound(yawControl),
        new WaitUntilWithCounter(() -> Limelight.getInstance().targetFound(), MIN_CHECKS_VISION),
        new MoveTurretByAngleAndKeep(yawControl, () -> visionTargetSupplier.getAsVisionTarget().getHorizontalOffset()).alongWith(new
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
