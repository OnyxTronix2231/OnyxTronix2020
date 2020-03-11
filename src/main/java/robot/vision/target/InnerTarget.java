package robot.vision.target;

import static robot.vision.VisionConstants.DISTANCE_BETWEEN_OUTER_INNER_TARGET;
import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;

import vision.limelight.target.LimelightTarget;

public class InnerTarget implements VisionTarget {

  private double horizontalOffset;
  private double verticalOffset;
  private double robotOrientation;
  private double distance;
  private double y;
  private OuterTarget outerTarget;

  InnerTarget(final OuterTarget target) {
    outerTarget = target;
    calculateByOuterTarget();
  }

  @Override
  public void update(final double accelerometerAngle, final double turretAngle, final LimelightTarget target) {
    outerTarget.update(accelerometerAngle, turretAngle, target);
    calculateByOuterTarget();
  }

  private void calculateByOuterTarget() {
    this.y = outerTarget.getY() + DISTANCE_BETWEEN_OUTER_INNER_TARGET;
    this.robotOrientation = Math.toDegrees(Math.atan(outerTarget.getX() / y));
    this.distance = Math.sqrt(Math.pow(outerTarget.getX(), 2) + Math.pow(y, 2));
    this.horizontalOffset = outerTarget.getHorizontalOffset() + (robotOrientation - outerTarget.getRobotOrientation());
    this.verticalOffset = Math.toDegrees(Math.atan((
        TARGET_HEIGHT_CM - outerTarget.getCameraHeight() + HEIGHT_OFFSET_INNER_OUTER_CENTER)));
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
  public double getRobotOrientation() {
    return robotOrientation;
  }

  @Override
  public double getDistance() {
    return distance;
  }

  @Override
  public double getCameraOffset() {
    return outerTarget.getCameraOffset();
  }

  @Override
  public double getCameraHeight() {
    return outerTarget.getCameraHeight();
  }

  @Override
  public double getX() {
    return outerTarget.getX();
  }

  @Override
  public double getY() {
    return y;
  }
}
