package robot.turret.commands;

import robot.turret.Turret;
import robot.vision.target.VisionTargetSupplier;

public class AlignByVision extends MoveTurretByAngleAndKeep {

  public AlignByVision(final Turret turret, final VisionTargetSupplier visionTargetSupplier) {
    super(turret, () -> visionTargetSupplier.getAsVisionTarget().getHorizontalOffset());
  }
}
