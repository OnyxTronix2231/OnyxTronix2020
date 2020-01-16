package robot.vision;

import static robot.vision.VisionConstants.TARGET_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_HEIGHT_CM;
import static robot.vision.VisionConstants.CAMERA_VERTICAL_OFFSET_ANGLE;

import robot.vision.limelight.Limelight;
import robot.vision.limelight.exception.TargetNotFoundException;

public class Calculations {


  public static double calculateDistance() {
    try {
      return (TARGET_HEIGHT_CM - CAMERA_HEIGHT_CM) / Math.tan(Math.toRadians(CAMERA_VERTICAL_OFFSET_ANGLE +
          Limelight.getInstance().getTarget().getHorizontalOffsetToCrosshair()));
    } catch (TargetNotFoundException e) {
      System.out.println("Target Was Not Found");
    }
    return 0;
  }

  public static double calculateDistanceToInnerTarget() {
    double length = 0;
    double width =
    final double orientation = Math.toDegrees(Math.acos());
  }
}
