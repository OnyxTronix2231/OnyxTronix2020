package robot.vision.target;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import vision.limelight.target.LimelightTarget;

public interface VisionTarget {

  void update(double turretAngle, LimelightTarget target);

  double getHorizontalOffset();

  double getVerticalOffset();

  double getTurretOrientation();

  double getDistance();

  double getCameraOffset();

  double getCameraHeight();

  double getTurretX();

  double getTurretY();

  double getRobotX();

  double getRobotY();
}
