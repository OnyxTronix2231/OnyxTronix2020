package robot.vision;

public final class VisionConstants {
  public static final double TARGET_HEIGHT_CM = 230; // 227.98 in competition
  public static final double TARGET_LENGTH_CM = 43.2;
  public static final double TARGET_WIDTH_CM = 100;
  public static final double TARGET_LENGTH_WIDTH_RATIO_CM = TARGET_LENGTH_CM / TARGET_WIDTH_CM;
  public static final double DISTANCE_BETWEEN_OUTER_INNER_TARGET = 29.25;
  public static final Vector2dEx VECTOR_OUTER_INNER_TARGET = new Vector2dEx(DISTANCE_BETWEEN_OUTER_INNER_TARGET, 0);
  public static final double HEIGHT_OFFSET_INNER_OUTER_CENTER = 22.02;
  public static final int MAX_INNER_DISTANCE = 700; // TODO: Check and Change
  public static final int MIN_INNER_DISTANCE = 290;
  public static final double MAX_INNER_ORIENTATION = 15;

  public static final class RobotAConstants {
    public static final double CAMERA_VERTICAL_OFFSET_ANGLE = 23.21; // TODO: Check and change
    public static final double CAMERA_HEIGHT_CM = 46.5; //TODO: Check and change
    public static final double LIMELIGHT_TURRET_CENTER_CM = 22.5;
    public static final Vector2dEx VECTOR_LIMELIGHT_TURRET_CENTER = new Vector2dEx(LIMELIGHT_TURRET_CENTER_CM, 0);
    public static final double ROBOT_CENTER_TURRET_DISTANCE = 0; // TODO: Check and change
  }
}
