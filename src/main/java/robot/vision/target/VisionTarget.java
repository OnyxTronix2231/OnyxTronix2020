package robot.vision.target;

import robot.vision.Vector2dEx;
import vision.limelight.target.LimelightTarget;

public interface VisionTarget {

  void update(double gyroYaw, double turretRTFAngle, LimelightTarget target);

  double getHorizontalOffset();

  double getVerticalOffset();

  double getTurretOrientation();

  double getDistance();

  double getCameraOffset();

  double getCameraHeight();

  Vector2dEx getTurretToTargetVector();

  Vector2dEx getRobotToTargetVector();
}
