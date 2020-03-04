package robot.vision.target;

import static robot.vision.VisionConstants.RobotAConstants.DISTANCE_TURRET_MIDDLE_ROBOT;
import static robot.vision.VisionConstants.RobotAConstants.LIMELIGHT_TURRET_CENTER_CM;
import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import robot.vision.VisionConstants;
import vision.limelight.target.LimelightTarget;

public class OuterTarget implements VisionTarget {

  private final double cameraOffset;
  private final double cameraHeight;
  private double horizontalOffset;
  private double verticalOffset;
  private double turretOrientation;
  private double distance;
  private double turretX;
  private double turretY;
  private double robotX;
  private double robotY;
  private double robotOrientation;
  private Pose2d pose2d;

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
      double turretDistance = distance + LIMELIGHT_TURRET_CENTER_CM;
      double turretOffset = Math.toDegrees(Math.asin(distance * Math.sin(
          Math.toRadians(target.getHorizontalOffsetToCrosshair())) / turretDistance));
      if(Double.isNaN(turretOffset)) turretOffset = 0;
      this.turretOrientation = turretAngle + accelerometerAngle + turretOffset;
      this.turretX = turretDistance * Math.sin(Math.toRadians(turretOrientation));
      this.turretY = turretDistance * Math.cos(Math.toRadians(turretOrientation));
      double robotDistance = turretDistance - DISTANCE_TURRET_MIDDLE_ROBOT;
      double robotOffset =  Math.toDegrees(Math.asin( turretDistance *
          Math.sin(Math.toRadians(turretOffset)) / robotDistance));
      this.robotOrientation = turretAngle + accelerometerAngle + robotOffset;
      this.robotX = robotDistance * Math.sin(Math.toRadians(robotOrientation));
      this.robotY = robotDistance * Math.cos(Math.toRadians(robotOrientation));
      this.pose2d = new Pose2d(new Translation2d(robotX, robotY), Rotation2d.fromDegrees(robotOrientation));
      this.horizontalOffset = target.getHorizontalOffsetToCrosshair();
    } else {
      this.horizontalOffset = 0;
      this.verticalOffset = 0;
      this.robotOrientation = 0;
      this.distance = 0;
      this.turretX = 0;
      this.turretY = 0;
      this.robotOrientation = 0;
      this.robotX = 0;
      this.robotY = 0;
      this.pose2d = new Pose2d(new Translation2d(0, 0), Rotation2d.fromDegrees(0));
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
  public double getTurretX() {
    return turretX;
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
    return robotX;
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
