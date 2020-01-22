package robot.vision.Target;

import static robot.vision.VisionConstants.CAMERA_HEIGHT_CM;
import static robot.vision.VisionConstants.DISTANCE_BETWEEN_OUTER_INNER_TARGET;
import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;

import robot.vision.Calculations;
import robot.vision.limelight.target.LimelightTarget;

public class VisionTarget {
  private final double horizontalOffset;
  private final double verticalOffset;
  private final double orientation;
  private final double distance;
  private final double x;
  private final double y;

  public VisionTarget(final double horizontalOffset, final double verticalOffset,
                      final double orientation, final double distance, final double x, final double y) {
    this.horizontalOffset = horizontalOffset;
    this.verticalOffset = verticalOffset;
    this.orientation = orientation;
    this.distance = distance;
    this.y = y;
    this.x = x;
  }

  public VisionTarget(double horizontalOffset, double verticalOffset, double orientation, double distance) {
    this.horizontalOffset = horizontalOffset;
    this.verticalOffset = verticalOffset;
    this.orientation = orientation;
    this.distance = distance;
    this.x = distance * Math.sin(Math.toRadians(orientation));
    this.y = distance * Math.cos(Math.toRadians(orientation));
  }

  public VisionTarget(final LimelightTarget target) {
    this.horizontalOffset = target.getHorizontalOffsetToCrosshair();
    this.verticalOffset = target.getVerticalOffsetToCrosshair();
    this.orientation = Calculations.calculateOrientationToTarget(target);
    this.distance = Calculations.calculateDistance(target);
    this.x = distance * Math.sin(Math.toRadians(orientation));
    this.y = distance * Math.cos(Math.toRadians(orientation));
  }

  public double getHorizontalOffset() {
    return horizontalOffset;
  }

  public double getVerticalOffset() {
    return verticalOffset;
  }

  public double getOrientation() {
    return orientation;
  }

  public double getDistance() {
    return distance;
  }

  public VisionTarget generateInnerTarget() {
    final double innerX = x;
    final double innerY = y + DISTANCE_BETWEEN_OUTER_INNER_TARGET;
    final double innerOrientation = Math.toDegrees(innerX / innerY);
    final double innerDistance = Math.sqrt(Math.pow(innerX, 2) + Math.pow(innerY, 2));
    final double innerHorizontalOffset = horizontalOffset - 90 + innerOrientation;
    final double innerVerticalOffset = Math.toDegrees(Math.atan(
        TARGET_HEIGHT_CM - CAMERA_HEIGHT_CM + HEIGHT_OFFSET_INNER_OUTER_CENTER) /
        innerDistance);

    return new VisionTarget(innerHorizontalOffset, innerVerticalOffset, innerOrientation, innerDistance, innerX, innerY);
  }
}
