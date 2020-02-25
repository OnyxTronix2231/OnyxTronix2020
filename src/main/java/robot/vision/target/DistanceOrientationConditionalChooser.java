package robot.vision.target;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import robot.vision.Vision;

public class DistanceOrientationConditionalChooser extends ConditionalTargetChooser {

  public DistanceOrientationConditionalChooser(final Vision vision, final int maxDistance,
                                               final int minDistance, final double maxAbsOrientation) {
    super(vision::getInnerTarget, vision::getOuterTarget, () ->
        vision.getOuterTarget().getDistance() < maxDistance &&
            Math.abs(vision.getOuterTarget().getOrientation()) < maxAbsOrientation);
    Shuffleboard.getTab("Vision").addBoolean("Can Hit Inner", condition);
  }
}
