package robot.vision.target;

import java.util.function.BooleanSupplier;

public class DistanceOrientationConditionalMaker extends ConditionalTargetMaker {

  public DistanceOrientationConditionalMaker(final VisionTargetFactory factory, final int maxDistance,
                                             final double maxAbsOrientation) {
    super(factory, () -> {
      VisionTarget target = factory.makeTarget(VisionTargetType.OUTER_TARGET);
      return target.getDistance() < maxDistance && Math.abs(target.getOrientation()) < maxAbsOrientation;
    });
  }
}
