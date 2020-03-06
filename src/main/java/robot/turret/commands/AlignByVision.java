package robot.turret.commands;

import robot.turret.Turret;
import robot.vision.target.VisionTargetSupplier;
import vision.limelight.Limelight;

public class AlignByVision extends MoveTurretByAngleAndKeep {

  public AlignByVision(final Turret turret, final VisionTargetSupplier visionTargetSupplier) {
    super(turret, () -> visionTargetSupplier.getAsVisionTarget().getHorizontalOffset());
  }

  @Override
  public boolean isFinished() {
    return super.isFinished() || Limelight.getInstance().targetFound();
  }
}
