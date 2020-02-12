package robot.vision.target;

import static robot.vision.VisionConstants.DISTANCE_BETWEEN_OUTER_INNER_TARGET;
import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;

import vision.limelight.target.LimelightTarget;

public class InnerTarget implements VisionTarget {

  private final double cameraOffset;
  private final double cameraHeight;
  private double horizontalOffset;
  private double verticalOffset;
  private double orientation;
  private double distance;
  private double x;
  private double y;
  private OuterTarget outerTarget;

  InnerTarget(final double cameraOffset, final double cameraHeight, final OuterTarget target) {
    this.cameraOffset = cameraOffset;
    this.cameraHeight = cameraHeight;
    outerTarget = target;
    calculateByOuterTarget();
  }

  @Override
  public void update(final double accelerometerAngle, final double turretAngle, final LimelightTarget target) {
    outerTarget.update(accelerometerAngle, turretAngle, target);
    calculateByOuterTarget();
  }

  private void calculateByOuterTarget() {
    this.x = outerTarget.getX();
    this.y = outerTarget.getY() + DISTANCE_BETWEEN_OUTER_INNER_TARGET;
    this.orientation = Math.toDegrees(Math.atan(x / y));
    this.distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    this.horizontalOffset = outerTarget.getHorizontalOffset() - 90 + orientation;
    this.verticalOffset = Math.toDegrees(Math.atan((
        TARGET_HEIGHT_CM - cameraHeight + HEIGHT_OFFSET_INNER_OUTER_CENTER)));
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
