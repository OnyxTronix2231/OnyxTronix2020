package robot.turret;

public class TurretConstants {

  public static final int MASTER_MOTOR_PORT = 11;
  public static final double SPEED = 0.2;
  public static final double CONVERSION_RATE = 4;
  public static final int ENCODER_UNITS = 1023;
  private static final int MAX_VELOCITY = 900;
  public static final double VELOCITY_P = 0.5;
  public static final double VELOCITY_I = 0;
  public static final double VELOCITY_D = 0;
  public static final double VELOCITY_F = ENCODER_UNITS/MAX_VELOCITY;
  public static final int DEGREES_IN_CIRCLE = 360;

}
