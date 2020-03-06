package robot.vision.target;

import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;
import static robot.vision.VisionConstants.RobotAConstants.VECTOR_LIMELIGHT_TURRET_CENTER;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;

import robot.vision.Vector2dEx;
import robot.vision.VisionConstants;
import vision.limelight.target.LimelightTarget;

public class InnerTarget implements VisionTarget {

  private double horizontalOffset;
  private double verticalOffset;
  private double turretOrientation;
  private double turretAngle;
  private double distance;
  private Vector2dEx turretToTargetVector;
  private OuterTarget outerTarget;

  InnerTarget(final OuterTarget target) {
    outerTarget = target;
    calculateByOuterTarget();
  }

  @Override
  public void update(final double turretAngle, final LimelightTarget target) {
    outerTarget.update(turretAngle, target);
    this.turretAngle = turretAngle;
    calculateByOuterTarget();
  }

  private void calculateByOuterTarget() {
    this.turretToTargetVector = outerTarget.getTurretToTargetVector().clone();
    this.turretToTargetVector.add(VisionConstants.VECTOR_OUTER_INNER_TARGET);
    this.turretOrientation = turretToTargetVector.direction();
    this.horizontalOffset = outerTarget.getHorizontalOffset() + (turretOrientation - outerTarget.getTurretOrientation());
    Vector2dEx offsetLimelightVector = Vector2dEx.fromMagnitudeDirection(turretToTargetVector.magnitude(), horizontalOffset);
    offsetLimelightVector.subtract(VECTOR_LIMELIGHT_TURRET_CENTER);
    this.distance = offsetLimelightVector.magnitude();
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
  public double getTurretOrientation() {
    return turretOrientation;
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
  public Vector2dEx getTurretToTargetVector() {
    return turretToTargetVector;
  }
}
