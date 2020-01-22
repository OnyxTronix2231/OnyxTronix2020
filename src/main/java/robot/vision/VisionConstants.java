package robot.vision;

public final class VisionConstants {
  public static final double KP = 0.06;
  public static final double KI = 0.00;
  public static final double KD = 0.01;
  public static final double TARGET_HEIGHT_CM = 230; // 227.98 in competition
  public static final double TARGET_LENGTH_CM = 43.2;
  public static final double TARGET_WIDTH_CM = 100;
  public static final double TARGET_LENGTH_WIDTH_RATIO_CM = TARGET_LENGTH_CM / TARGET_WIDTH_CM;
  public static final double CAMERA_VERTICAL_OFFSET_ANGLE = 47.4;
  public static final double CAMERA_HEIGHT_CM = 35.5;
  public static final double DISTANCE_BETWEEN_OUTER_INNER_TARGET = 29.25;
  public static final double HEIGHT_OFFSET_INNER_OUTER_CENTER = 22.02;
}
