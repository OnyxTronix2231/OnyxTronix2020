package robot.vision.target;

import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;

import robot.vision.Vector2dEx;
import robot.vision.VisionConstants;
import vision.limelight.target.LimelightTarget;

public class OuterTarget implements VisionTarget {

  private final double cameraOffset;
  private final double cameraHeight;
  private double horizontalOffset;
  private double verticalOffset;
  private double turretOrientation;
  private double distance;
  private Vector2dEx turretToTargetVector;

  OuterTarget(final double turretAngle,
              final double cameraHeight, final double cameraOffset, final LimelightTarget target) {
    this.cameraHeight = cameraHeight;
    this.cameraOffset = cameraOffset;
    update(turretAngle, target);
  }

  @Override
  public void update(final double turretAngle, final LimelightTarget target) {
    if (target != null) {
      this.verticalOffset = target.getVerticalOffsetToCrosshair();
      this.distance = (TARGET_HEIGHT_CM - cameraHeight) / Math.tan(Math.toRadians(cameraOffset +
          target.getVerticalOffsetToCrosshair()));
      Vector2dEx offsetTurretVector = Vector2dEx.fromMagnitudeDirection(distance, target.getHorizontalOffsetToCrosshair());
      offsetTurretVector.add(VisionConstants.RobotAConstants.VECTOR_LIMELIGHT_TURRET_CENTER);
      double turretDistance = offsetTurretVector.magnitude();
      double turretOffset = offsetTurretVector.direction();
      if(Double.isNaN(turretOffset)) turretOffset = 0;
      this.turretOrientation = Math.IEEEremainder(turretAngle, 360) + turretOffset;
      this.turretToTargetVector = Vector2dEx.fromMagnitudeDirection(turretDistance, turretOrientation);
      this.horizontalOffset = target.getHorizontalOffsetToCrosshair();
    } else {
      this.horizontalOffset = 0;
      this.verticalOffset = 0;
      this.distance = 0;
      this.turretOrientation = 0;
      this.turretToTargetVector = new Vector2dEx(0, 0);
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
  public double getTurretOrientation() {
    return turretOrientation;
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
  public Vector2dEx getTurretToTargetVector() {
    return turretToTargetVector;
  }
}
