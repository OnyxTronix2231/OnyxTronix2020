package robot.vision.target;

import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;
import static robot.vision.VisionConstants.RobotAConstants.ROBOT_CENTER_TURRET_DISTANCE;
import static robot.vision.VisionConstants.RobotAConstants.VECTOR_LIMELIGHT_TURRET_CENTER;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;
import static robot.vision.VisionConstants.VECTOR_OUTER_INNER_TARGET;

import robot.vision.Vector2dEx;
import vision.limelight.target.LimelightTarget;

public class InnerTarget implements VisionTarget {

  private double horizontalOffset;
  private double verticalOffset;
  private double turretOrientation;
  private double distance;
  private double gyroYaw;
  private Vector2dEx turretToTargetVector;
  private Vector2dEx robotToTargetVector;
  private OuterTarget outerTarget;

  InnerTarget(final double gyroYaw ,final OuterTarget target) {
    outerTarget = target;
    this.gyroYaw = gyroYaw;
    calculateByOuterTarget();
  }

  @Override
  public void update(final double gyroYaw ,final double turretAngle, final LimelightTarget target) {
    outerTarget.update(gyroYaw ,turretAngle, target);
    this.gyroYaw = gyroYaw;
    calculateByOuterTarget();
  }

  private void calculateByOuterTarget() {
    this.turretToTargetVector = outerTarget.getTurretToTargetVector();
    this.turretToTargetVector.add(VECTOR_OUTER_INNER_TARGET);
    this.turretOrientation = turretToTargetVector.direction();
    this.horizontalOffset = outerTarget.getHorizontalOffset() + (turretOrientation - outerTarget.getTurretOrientation());
    Vector2dEx limelightCenterToTargetVector = Vector2dEx.fromMagnitudeDirection(turretToTargetVector.magnitude(), horizontalOffset);
    limelightCenterToTargetVector.subtract(VECTOR_LIMELIGHT_TURRET_CENTER);
    this.distance = limelightCenterToTargetVector.magnitude();
    this.verticalOffset = Math.toDegrees(Math.atan2((
        TARGET_HEIGHT_CM - outerTarget.getCameraHeight() + HEIGHT_OFFSET_INNER_OUTER_CENTER), distance));
    Vector2dEx turretToRobotCenterVector = Vector2dEx.fromMagnitudeDirection(ROBOT_CENTER_TURRET_DISTANCE, gyroYaw);
    this.robotToTargetVector = turretToTargetVector.clone();
    robotToTargetVector.subtract(turretToRobotCenterVector);
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
    return turretToTargetVector.clone();
  }

  @Override
  public Vector2dEx getRobotToTargetVector() {
    return robotToTargetVector.clone();
  }
}
