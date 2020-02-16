package robot.yawControl.commands;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import robot.turret.Turret;
import robot.turret.commands.MoveTurretToAngleAndKeep;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class MoveTurretToFieldTargetByPose extends MoveTurretToAngleAndKeep {

  private final Supplier<Pose2d> pose2dSupplier;
  private Pose2d pose2d;

  public MoveTurretToFieldTargetByPose(final Turret turret, final DoubleSupplier angleSupplier,
                                       final Supplier<Pose2d> pose2dSupplier) {
    super(turret, angleSupplier);
    this.pose2dSupplier = pose2dSupplier;
  }

  @Override
  public void initialize() {
    pose2d = pose2dSupplier.get();
  }
}