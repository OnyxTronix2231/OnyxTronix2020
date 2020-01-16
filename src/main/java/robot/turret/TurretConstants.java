package robot.turret;

public class TurretConstants {

  public static final int MASTER_MOTOR_PORT = 11;
  public static final double SPEED = 0.2;
  public static final int ENCODER_UNITS = 4096;
  public static final int MAX_VELOCITY = 2500;
  public static final double VELOCITY_P = 1;
  public static final double VELOCITY_I = 0;
  public static final double VELOCITY_D = 0;
  public static final double VELOCITY_F = (double) 1023 / MAX_VELOCITY;
  public static final int TOLERANCE = 10;
  public static final int MAX_ACCELERATION = 1000;
  public static final double CONVERSION_RATE = 4;
  public static final double DEGREES_IN_CIRCLE = 360;
  public static double ENCODER_TO_ANGLE = DEGREES_IN_CIRCLE / (ENCODER_UNITS * CONVERSION_RATE);
  public static final int PICK_AMP = 40;
  public static final int PICK_AMP_DURATION = 0;
  public static final int CONTINUES_CURRENT_LIMIT = 40;
}
