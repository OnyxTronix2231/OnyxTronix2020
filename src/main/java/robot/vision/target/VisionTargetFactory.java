package robot.vision.target;

import vision.limelight.Limelight;
import java.util.function.DoubleSupplier;

public class VisionTargetFactory {

  private final Limelight limelight;
  private final DoubleSupplier turretAngleSupplier;
  private final DoubleSupplier accelerometerAngleSupplier;
  private final double cameraHeight;
  private final double cameraOffset;


  public VisionTargetFactory(final DoubleSupplier turretAngleSupplier, final DoubleSupplier accelerometerAngleSupplier,
                             final double cameraAngleOffset, final double cameraHeight,final Limelight limelight) {
    this.limelight = limelight;
    this.turretAngleSupplier = turretAngleSupplier;
    this.accelerometerAngleSupplier = accelerometerAngleSupplier;
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
    return new OuterTarget(accelerometerAngleSupplier.getAsDouble(),
        turretAngleSupplier.getAsDouble(),cameraHeight, cameraOffset ,limelight.getTarget());
  }

  private InnerTarget generateInnerTarget() {
    return new InnerTarget(generateOuterTarget());
  }
}
