package robot.vision.target;

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
      double turretDistance = Math.sqrt(Math.pow(distance * Math.sin(Math.toRadians(target.getHorizontalOffsetToCrosshair())), 2)
      + Math.pow(distance * Math.cos(Math.toRadians(target.getHorizontalOffsetToCrosshair()))
          + LIMELIGHT_TURRET_CENTER_CM, 2));
      double turretOffset = Math.toDegrees(Math.asin(distance * Math.sin(
          Math.toRadians(target.getHorizontalOffsetToCrosshair())) / turretDistance));
      if(Double.isNaN(turretOffset)) turretOffset = 0;
      this.turretOrientation = Math.IEEEremainder(turretAngle, 360) + turretOffset;
      this.turretX = turretDistance * Math.sin(Math.toRadians(turretOrientation));
      this.turretY = turretDistance * Math.cos(Math.toRadians(turretOrientation));
      this.horizontalOffset = target.getHorizontalOffsetToCrosshair();
      double robotDriverStationDistance = (turretY / Math.cos(Math.toRadians(turretAngle))) +
          VisionConstants.RobotAConstants.ROBOT_TURRET_DISTANCE;
      this.robotY = robotDriverStationDistance * Math.cos(Math.toRadians(turretAngle));
      this.robotX = robotDriverStationDistance * Math.sin(Math.toRadians(turretAngle)) -
          turretDistance * Math.sin(Math.toRadians(turretAngle)) + turretX;
    } else {
      this.horizontalOffset = 0;
      this.verticalOffset = 0;
      this.distance = 0;
      this.turretOrientation = 0;
      this.turretX = 0;
      this.turretY = 0;
      this.robotX = 0;
      this.robotY = 0;
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
  public double getRobotX() {
    return robotX;
  }

  @Override
  public double getRobotY() {
    return robotY;
  }
}
