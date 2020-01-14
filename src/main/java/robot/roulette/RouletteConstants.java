package robot.roulette;

public final class RouletteConstants {
  public static int MASTER_MOTOR_PORT = 0;
  public static int DOUBLE_SOLENOID_FORWARD_CHANNEL = 0;
  public static int DOUBLE_SOLENOID_REVERSE_CHANNEL = 1;
  public static double SPIN_COLOR_SPEED = 0.3;
  public static double SPIN_ROTATIONS_SPEED = 0.7;
  public static int COLORS_IN_ROTATIONS = 8;
  public static int MAX_ROTATIONS = 5;
  public static int MIN_ROTATIONS = 3;
  public static int AVERAGE_ROTATIONS = (MIN_ROTATIONS + MAX_ROTATIONS)/2;
  public static int REQUIRED_ROTATIONS = AVERAGE_ROTATIONS *  COLORS_IN_ROTATIONS;
  public static final int PICK_AMP = 40;
  public static final int PICK_AMP_DURATION = 0;
  public static RGBValue GREEN_RGB_VALUE = new RGBValue(0.149, 0.599, 0.250);
  public static RGBValue BLUE_RGB_VALUE = new RGBValue(0.108, 0.428, 0.462);
  public static RGBValue RED_RGB_VALUE = new RGBValue(0.551, 0.329, 0.119);
  public static RGBValue YELLOW_RGB_VALUE = new RGBValue(0.319, 0.571, 0.108);

}
