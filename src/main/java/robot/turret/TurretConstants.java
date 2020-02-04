package robot.turret;

public class TurretConstants {

  public static final int MASTER_MOTOR_PORT = 11;
  public static final int MAX_VELOCITY = 3100;
  public static final int MAX_OUTPUT = 1023;
  public static final int TOLERANCE = 10;
  public static final int MAX_ACCELERATION = 2000;
  public static final int PEAK_AMP = 40;
  public static final int PEAK_AMP_DURATION = 0;
  public static final int CONTINUOUS_CURRENT_LIMIT = 40;
  public static final int FLIP_POINT = 180;
  public static final int MAX_ANGLE = 135;
  public static final int MIN_ANGLE = -135;
  public static final int ENCODER_UNITS = 1023;
  public static final int DEGREES_IN_CIRCLE = 360;
  public static final double CONVERSION_RATE = 4;
  public static final double ENCODER_TO_ANGLE = DEGREES_IN_CIRCLE / (ENCODER_UNITS * CONVERSION_RATE);
  public static final double VELOCITY_P = 5;
  public static final double VELOCITY_I = 0;
  public static final double VELOCITY_D = 2;
  public static final double VELOCITY_F = (double) MAX_OUTPUT / MAX_VELOCITY;
}
