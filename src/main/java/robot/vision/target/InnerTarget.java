package robot.vision.target;

import static robot.vision.VisionConstants.DISTANCE_BETWEEN_OUTER_INNER_TARGET;
import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;
import static robot.vision.VisionConstants.RobotAConstants.LIMELIGHT_TURRET_CENTER_CM;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;


import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import robot.vision.VisionConstants;
import vision.limelight.target.LimelightTarget;

public class InnerTarget implements VisionTarget {

  private double horizontalOffset;
  private double verticalOffset;
  private double turretOrientation;
  private double turretAngle;
  private double distance;
  private double turretY;
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
    this.turretY = outerTarget.getTurretY() + DISTANCE_BETWEEN_OUTER_INNER_TARGET;
    this.turretOrientation = Math.toDegrees(Math.atan(outerTarget.getTurretX() / turretY));
    double turretDistance = Math.sqrt(Math.pow(outerTarget.getTurretX(), 2) + Math.pow(turretY, 2));
    this.distance = turretDistance - LIMELIGHT_TURRET_CENTER_CM;
    this.horizontalOffset = outerTarget.getHorizontalOffset() + (turretOrientation - outerTarget.getTurretOrientation());
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
  public double getTurretX() {
    return outerTarget.getTurretX();
  }

  @Override
  public double getTurretY() {
    return turretY;
  }
}
