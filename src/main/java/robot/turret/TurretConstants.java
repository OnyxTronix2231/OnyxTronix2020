package robot.turret;

final class TurretConstants {

  static final class RobotAComponents {
    static final int MASTER_MOTOR_PORT = 11; //TODO change
    static final int PEAK_AMP = 40; //TODO change
    static final int PEAK_AMP_DURATION = 0; //TODO change
    static final int CONTINUOUS_CURRENT_LIMIT = 40; //TODO change
    static final int MAX_ACCELERATION = 2000; //TODO change
    static final int MAX_VELOCITY = 3100; //TODO change
    static final int MAX_OUTPUT = 1023;
    static final double VELOCITY_P = 5; //TODO change
    static final double VELOCITY_I = 0; //TODO change
    static final double VELOCITY_D = 2; //TODO change
    static final double VELOCITY_F = (double) MAX_OUTPUT / MAX_VELOCITY;

  }

  static final int TOLERANCE = 10;
  static final int FLIP_POINT = 180;
  static final int MAX_ANGLE = 135;
  static final int MIN_ANGLE = -135;
  private static final int ENCODER_UNITS = 1023;
  static final int DEGREES_IN_CIRCLE = 360;
  private static final double CONVERSION_RATE = 114.375;
  static final double ENCODER_TO_ANGLE = DEGREES_IN_CIRCLE / (ENCODER_UNITS * CONVERSION_RATE);
}
