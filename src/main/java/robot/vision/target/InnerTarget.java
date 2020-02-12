package robot.vision.target;

import static robot.vision.VisionConstants.DISTANCE_BETWEEN_OUTER_INNER_TARGET;
import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_HEIGHT_CM;

import vision.limelight.target.LimelightTarget;

public class InnerTarget implements VisionTarget {

  private double horizontalOffset;
  private double verticalOffset;
  private double orientation;
  private double distance;
  private double x;
  private double y;
  private OuterTarget outerTarget;

  InnerTarget(final OuterTarget target) {
    this.x = target.getX();
    this.y = target.getY() + DISTANCE_BETWEEN_OUTER_INNER_TARGET;
    this.orientation = Math.toDegrees(Math.atan(x / y));
    this.distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    this.horizontalOffset = target.getHorizontalOffset() - 90 + orientation;
    this.verticalOffset = Math.toDegrees(Math.atan(
        TARGET_HEIGHT_CM - CAMERA_HEIGHT_CM + HEIGHT_OFFSET_INNER_OUTER_CENTER) /
        distance);
    outerTarget = target;
  }

  @Override
  public void update(final LimelightTarget target, final double accelerometerAngle, final double turretAngle) {
    outerTarget.update(target, accelerometerAngle, turretAngle);
    this.x = outerTarget.getX();
    this.y = outerTarget.getY() + DISTANCE_BETWEEN_OUTER_INNER_TARGET;
    this.orientation = Math.toDegrees(Math.atan(x / y));
    this.distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    this.horizontalOffset = outerTarget.getHorizontalOffset() - 90 + orientation;
    this.verticalOffset = Math.toDegrees(Math.atan(
        TARGET_HEIGHT_CM - CAMERA_HEIGHT_CM + HEIGHT_OFFSET_INNER_OUTER_CENTER) /
        distance);
  }

  @Override
  public double getHorizontalOffset() {
    return horizontalOffset;
  }

  @Override
  public double getVerticalOffset() {
    return verticalOffset;
  }

  @Override
  public double getOrientation() {
    return orientation;
  }

  @Override
  public double getDistance() {
    return distance;
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }
}
