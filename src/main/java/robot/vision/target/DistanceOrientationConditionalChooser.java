package robot.vision.target;

import robot.vision.Vision;

public class DistanceOrientationConditionalChooser extends ConditionalTargetChooser {

  public DistanceOrientationConditionalChooser(final Vision vision, final int maxDistance,
                                               final double maxAbsOrientation) {
    super(vision::getInnerTarget, vision::getOuterTarget, () ->
        vision.getOuterTarget().getDistance() < maxDistance &&
            Math.abs(vision.getOuterTarget().getOrientation()) < maxAbsOrientation);
  }
}
