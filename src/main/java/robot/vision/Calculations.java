package robot.vision;

import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_VERTICAL_OFFSET_ANGLE;
import static robot.vision.VisionConstants.TARGET_LENGTH_CM;
import static robot.vision.VisionConstants.TARGET_WIDTH_CM;

import org.opencv.video.Video;
import robot.vision.limelight.Limelight;
import robot.vision.limelight.exception.TargetNotFoundException;
import robot.vision.limelight.target.LimelightTarget;

public class Calculations {


  public static double calculateDistance(final LimelightTarget target) {
    return (TARGET_HEIGHT_CM - CAMERA_HEIGHT_CM) / Math.tan(Math.toRadians(CAMERA_VERTICAL_OFFSET_ANGLE +
        target.getVerticalOffsetToCrosshair()));
  }

  public static double calculateOrientationToTarget(final LimelightTarget target) {
    return Math.atan((target.getShortSideOfFittedBoundingBox()/ target.getLongSideOfFittedBoundingBox()) /
        (TARGET_LENGTH_CM / TARGET_WIDTH_CM));
  }

  public static double getHorizontalOffsetInnerTarget(LimelightTarget target, double orientation) {
    return 0;
  }
}
