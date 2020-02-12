package robot.vision.target;

import vision.limelight.target.LimelightTarget;

public interface VisionTarget {

  void update( double accelerometerAngle, double turretAngle, LimelightTarget target);

  double getHorizontalOffset();

  double getVerticalOffset();

  double getOrientation();

  double getDistance();

  double getX();

  double getY();

}
