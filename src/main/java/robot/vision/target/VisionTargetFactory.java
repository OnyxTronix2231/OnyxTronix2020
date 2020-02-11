package robot.vision.target;

import robot.vision.limelight.Limelight;

import java.util.function.DoubleSupplier;

public class VisionTargetFactory {

  private final Limelight limelight;
  private final DoubleSupplier turretAngleSupplier;
  private final DoubleSupplier accelerometerAngleSupplier;

  /**
   * @param limelight                  for retrieving raw targets
   * @param turretAngleSupplier
   * @param accelerometerAngleSupplier
   */
  public VisionTargetFactory(final DoubleSupplier turretAngleSupplier, final DoubleSupplier accelerometerAngleSupplier,
                             final Limelight limelight) {
    this.limelight = limelight;
    this.turretAngleSupplier = turretAngleSupplier;
    this.accelerometerAngleSupplier = accelerometerAngleSupplier;
  }

  public final VisionTarget makeTarget(final VisionTargetType visionTargetType) {
    if (visionTargetType == VisionTargetType.outerTarget) {
      return generateOuterTarget();
    } else {
      return generateInnerTarget();
    }
  }

  private OuterTarget generateOuterTarget() {
    return new OuterTarget(limelight.getTarget(), accelerometerAngleSupplier.getAsDouble(),
        turretAngleSupplier.getAsDouble());
  }

  private InnerTarget generateInnerTarget() {
    return new InnerTarget(generateOuterTarget());
  }
}
