package robot.vision.target;

import vision.limelight.target.LimelightTarget;

public interface VisionTarget {

  void update(LimelightTarget target, double accelerometerAngle, double turretAngle);

  double getHorizontalOffset();

  double getVerticalOffset();

  double getOrientation();

  double getDistance();

  double getX();

  double getY();

}
