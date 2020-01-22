package robot.vision;

import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_VERTICAL_OFFSET_ANGLE;
import static robot.vision.VisionConstants.TARGET_LENGTH_WIDTH_RATIO_CM;

import robot.vision.limelight.target.LimelightTarget;

public class Calculations {


  public static double calculateDistance(final LimelightTarget target) {
    return (TARGET_HEIGHT_CM - CAMERA_HEIGHT_CM) / Math.tan(Math.toRadians(CAMERA_VERTICAL_OFFSET_ANGLE +
        target.getVerticalOffsetToCrosshair()));
  }

  public static double calculateOrientationToTarget(final LimelightTarget target) {
    return Math.toDegrees(Math.atan((target.getShortSideOfFittedBoundingBox() / target.getLongSideOfFittedBoundingBox())
        / TARGET_LENGTH_WIDTH_RATIO_CM));
  }
}
