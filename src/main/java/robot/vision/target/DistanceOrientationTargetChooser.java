package robot.vision.target;

import robot.vision.Vision;

public class DistanceOrientationTargetChooser extends TargetChooser {

  public DistanceOrientationTargetChooser(final Vision vision, final int maxDistance,
                                          final int minDistance, final double maxAbsOrientation) {
    super(vision::getInnerTarget, vision::getOuterTarget, () ->
        vision.getOuterTarget().getDistance() < maxDistance &&
        vision.getOuterTarget().getDistance() > minDistance &&
            Math.abs(vision.getOuterTarget().getTurretOrientation()) < maxAbsOrientation);
  }
}
