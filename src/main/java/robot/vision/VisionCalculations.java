package robot.vision;

import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_VERTICAL_OFFSET_ANGLE;

import vision.

public class VisionCalculations {

  public static double calculateDistance(final LimelightTarget target) {
    if (target == null) return 0;
    return (TARGET_HEIGHT_CM - CAMERA_HEIGHT_CM) / Math.tan(Math.toRadians(CAMERA_VERTICAL_OFFSET_ANGLE +
        target.getVerticalOffsetToCrosshair()));
  }

  public static double calculateOrientationToTarget(final LimelightTarget target, final double accelerometerAngle,
                                                    final double turretAngle) {
    return target.getHorizontalOffsetToCrosshair() + accelerometerAngle - turretAngle;
  }
}
