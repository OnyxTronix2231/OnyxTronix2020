package robot.vision.target;

import vision.limelight.Limelight;

import java.util.function.DoubleSupplier;

public class VisionTargetFactory {

  private final DoubleSupplier turretAngleSupplier;
  private final DoubleSupplier gyroAngleSupplier;
  private final double cameraHeight;
  private final double cameraOffset;

  public VisionTargetFactory(final DoubleSupplier gyroAngleSupplier, final DoubleSupplier turretAngleSupplier,
                             final double cameraAngleOffset, final double cameraHeight) {
    this.turretAngleSupplier = turretAngleSupplier;
    this.gyroAngleSupplier = gyroAngleSupplier;
    this.cameraHeight = cameraHeight;
    this.cameraOffset = cameraAngleOffset;
  }

  public final VisionTarget makeTarget(final VisionTargetType visionTargetType) {
    if (visionTargetType == VisionTargetType.OUTER_TARGET) {
      return generateOuterTarget();
    } else {
      return generateInnerTarget();
    }
  }

  private OuterTarget generateOuterTarget() {
    return new OuterTarget(gyroAngleSupplier.getAsDouble(), turretAngleSupplier.getAsDouble(),
        cameraHeight, cameraOffset, Limelight.getInstance().getTarget());
  }

  private InnerTarget generateInnerTarget() {
    return new InnerTarget(gyroAngleSupplier.getAsDouble(), generateOuterTarget());
  }
}
