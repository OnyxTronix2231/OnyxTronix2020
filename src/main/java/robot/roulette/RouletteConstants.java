package robot.roulette;

public final class RouletteConstants {

  public static final int MINUS_ONE = -1;
  static final RGBValue GREEN_RGB_VALUE = new RGBValue(0.149, 0.599, 0.250);
  static final RGBValue BLUE_RGB_VALUE = new RGBValue(0.108, 0.428, 0.462);
  static final RGBValue RED_RGB_VALUE = new RGBValue(0.551, 0.329, 0.119);
  static final RGBValue YELLOW_RGB_VALUE = new RGBValue(0.319, 0.571, 0.108);
  static final int TOLERANCE = 50; // TODO: check and change
  static final int COLORS_IN_ROTATIONS = 8;
  static final int HOW_CLOSE_TO_PERCENT = 1;
  static final int POW = 2;
  static final int ENCODER_UNITS_PER_ROTATION = 4000;
  static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
  static final double SPIN_COLOR_SPEED = 0.3; // TODO: check and change
  static final double SPIN_ROTATIONS_SPEED = 0.7; // TODO: check and change
  static final double RGB_EQUALS_THRESHOLD = 0.8; // TODO: check and change
  static final int DISTANCE_FROM_FIELD_SENSOR = 2; // TODO: check and change
  static final int MAX_ROTATIONS = 5;
  static final int MIN_ROTATIONS = 3;
  static final int AVERAGE_ROTATIONS = (MIN_ROTATIONS + MAX_ROTATIONS) / 2;
  public static final int AVERAGE_COLOR_COUNT = AVERAGE_ROTATIONS * COLORS_IN_ROTATIONS;
  private static final double ROULETTE_RADIUS = 41;

  public static final class RouletteComponentsA {

    static final int MASTER_MOTOR_PORT = 11; // TODO: check and change
    static final int PEAK_AMP = 40; // TODO: check and change
    static final int PEAK_AMP_DURATION = 1; // TODO: check and change
    static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check and change
    static final int DOUBLE_RIGHT_SOLENOID_REVERSE_CHANNEL = 1;
    static final int DOUBLE_RIGHT_SOLENOID_FORWARD_CHANNEL = 0;
    static final int DOUBLE_LEFT_SOLENOID_REVERSE_CHANNEL = 2;
    static final int DOUBLE_LEFT_SOLENOID_FORWARD_CHANNEL = 3;
    static final int MAX_VELOCITY = 8000; // TODO: check and change
    static final int MAX_ACCELERATION = 8000; // TODO: check and change
    static final int MOTION_CURVE_STRENGTH = 0;  // TODO: check and change
    static final double WHEEL_RADIUS = 5; // TODO: check and change
    static final double WHEEL_PERIMETER = 2 * Math.PI * WHEEL_RADIUS;
    static final double ROULETTE_PERIMETER = 2 * Math.PI * ROULETTE_RADIUS;
    static final double ROULETTE_ROTATION_TO_WHEEL_ROTATION = ROULETTE_PERIMETER / WHEEL_PERIMETER;
    static final double VELOCITY_P = 0.1; // TODO: check and change
    static final double VELOCITY_I = 0; // TODO: check and change
    static final double VELOCITY_D = 0; // TODO: check and change

  }
}
