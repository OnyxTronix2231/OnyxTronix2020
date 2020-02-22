package robot.vision.target;

import static robot.vision.VisionConstants.RobotAConstants.LIMELIGHT_TURRET_CENTER_CM;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;

import vision.limelight.target.LimelightTarget;

public class OuterTarget implements VisionTarget {

  private final double cameraOffset;
  private final double cameraHeight;
  private double horizontalOffset;
  private double verticalOffset;
  private double orientation;
  private double distance;
  private double x;
  private double y;

  OuterTarget(final double accelerometerAngle, final double turretAngle,
              final double cameraHeight, final double cameraOffset, final LimelightTarget target) {
    this.cameraHeight = cameraHeight;
    this.cameraOffset = cameraOffset;
    update(accelerometerAngle, turretAngle, target);
  }

  @Override
  public void update(final double accelerometerAngle, final double turretAngle, final LimelightTarget target) {
    if (target != null) {
      this.verticalOffset = target.getVerticalOffsetToCrosshair();
      this.distance = (TARGET_HEIGHT_CM - cameraHeight) / Math.tan(Math.toRadians(cameraOffset +
          target.getVerticalOffsetToCrosshair()));
      double limelightOrientation = -target.getHorizontalOffsetToCrosshair() + accelerometerAngle - turretAngle;
      double limelightX = distance * Math.sin(Math.toRadians(limelightOrientation));
      double limelightY = distance * Math.cos(Math.toRadians(limelightOrientation));
      this.x = limelightX;
      this.y = limelightY + LIMELIGHT_TURRET_CENTER_CM;
      this.orientation = Math.toDegrees(Math.asin(x / y));
      this.horizontalOffset = target.getHorizontalOffsetToCrosshair();
    } else {
      this.horizontalOffset = 0;
      this.verticalOffset = 0;
      this.orientation = 0;
      this.distance = 0;
      this.x = 0;
      this.y = 0;
    }
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
  public double getCameraOffset() {
    return cameraOffset;
  }

  @Override
  public double getCameraHeight() {
    return cameraHeight;
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
