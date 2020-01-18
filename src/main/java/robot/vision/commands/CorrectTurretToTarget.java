package robot.vision.commands;

import robot.turret.Turret;
import robot.turret.commands.MoveByAngle;
import robot.vision.limelight.Limelight;
import robot.vision.limelight.target.LimelightTarget;

import java.util.function.Supplier;

public class CorrectTurretToTarget extends MoveByAngle {
  public CorrectTurretToTarget(Turret turret, Supplier<LimelightTarget> target) {
    super(turret, () -> {
      if (target.get() == null) return 0;
      else return target.get().getHorizontalOffsetToCrosshair();
    });
  }
}
