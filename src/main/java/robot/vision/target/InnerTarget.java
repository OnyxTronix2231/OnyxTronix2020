package robot.vision.target;

import static robot.vision.VisionConstants.DISTANCE_BETWEEN_OUTER_INNER_TARGET;
import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;
import static robot.vision.VisionConstants.RobotAConstants.DISTANCE_TURRET_MIDDLE_ROBOT;
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
  private double accelerometerAngle;
  private double turretAngle;
  private double distance;
  private double turretY;
  private double robotY;
  private double robotOrientation;
  private OuterTarget outerTarget;
  private Pose2d pose2d;

  InnerTarget(final OuterTarget target) {
    outerTarget = target;
    calculateByOuterTarget();
  }

  @Override
  public void update(final double accelerometerAngle, final double turretAngle, final LimelightTarget target) {
    outerTarget.update(accelerometerAngle, turretAngle, target);
    this.accelerometerAngle = accelerometerAngle;
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
    double robotDistance = turretDistance - DISTANCE_TURRET_MIDDLE_ROBOT;
    double robotOffset = Math.toDegrees(Math.asin(turretDistance * Math.sin(Math.toRadians(horizontalOffset)) / robotDistance));
    this.robotOrientation = turretAngle + accelerometerAngle + robotOffset;
    this.robotY = robotDistance * Math.sin(Math.toRadians(robotOrientation));
    this.pose2d = new Pose2d(new Translation2d(outerTarget.getRobotX(), robotY), Rotation2d.fromDegrees(robotOrientation));
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

  @Override
  public double getRobotOrientation() {
    return robotOrientation;
  }

  @Override
  public double getRobotX() {
    return outerTarget.getRobotX();
  }

  @Override
  public double getRobotY() {
    return robotY;
  }

  @Override
  public Pose2d getPose2D() {
    return pose2d;
  }
}
