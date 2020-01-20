package robot.roulette;

public final class RouletteConstants {
  public final static int MASTER_MOTOR_PORT = 0;
  public final static int DOUBLE_SOLENOID_FORWARD_CHANNEL = 0;
  public final static int DOUBLE_SOLENOID_REVERSE_CHANNEL = 1;
  public final static double SPIN_COLOR_SPEED = 0.3;
  public final static double SPIN_ROTATIONS_SPEED = 0.7;
  public final static int COLORS_IN_ROTATIONS = 8;
  public  static int ENCODER_UNITS_PER_ROTATION;
  public final static int ROULETTE_ROTATION_TO_WHEEL_ROTATION = 2;
  public final static int TOLERANCE = 50;
  public final static int MAX_ROTATIONS = 5;
  public final static int MIN_ROTATIONS = 3;
  public final static int AVERAGE_ROTATIONS = (MIN_ROTATIONS + MAX_ROTATIONS)/2;
  public static int REQUIRED_ROTATIONS = AVERAGE_ROTATIONS *  COLORS_IN_ROTATIONS;
  public static final int PICK_AMP = 40; // TODO: check and change
  public static final int PICK_AMP_DURATION = 1; // TODO: check and change
  public static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check and change
  public static final double DISTANCE_FROM_FIELD_SENSOR = 2.0; // TODO: check and change
  public static RGBValue GREEN_RGB_VALUE = new RGBValue(0.149, 0.599, 0.250);
  public static RGBValue BLUE_RGB_VALUE = new RGBValue(0.108, 0.428, 0.462);
  public static RGBValue RED_RGB_VALUE = new RGBValue(0.551, 0.329, 0.119);
  public static RGBValue YELLOW_RGB_VALUE = new RGBValue(0.319, 0.571, 0.108);

}
