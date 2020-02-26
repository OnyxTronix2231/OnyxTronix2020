package robot.turret;

final class TurretConstants {

  static final class TurretComponentsA {
    static final int MASTER_MOTOR_PORT = 6;
    static final int PEAK_AMP = 40; //TODO check and change
    static final int PEAK_AMP_DURATION = 0; //TODO check and change
    static final int CONTINUOUS_CURRENT_LIMIT = 40; //TODO check and change
    static final int MAX_ACCELERATION = 1000; //TODO check and change
    static final int MAX_VELOCITY = 200; //TODO check and change
    static final int MAX_OUTPUT = 1023;
    static final int ABSOLUTE_ENCODER_OFFSET = 512;
    static final int FORWARD_SOFT_LIMIT_THRESHOLD = 613 + ABSOLUTE_ENCODER_OFFSET;
    static final int REVERSE_SOFT_LIMIT_THRESHOLD = ABSOLUTE_ENCODER_OFFSET - 357;
    static final boolean FORWARD_SOFT_LIMIT_ENABLE = true;
    static final boolean REVERSE_SOFT_LIMIT_ENABLE = true;
    static final double VELOCITY_P = 25; //TODO check and change
    static final double VELOCITY_I = 0.1; //TODO check and change
    static final double VELOCITY_D = 50; //TODO check and change
    static final double VELOCITY_F = (double) MAX_OUTPUT / MAX_VELOCITY;
  }

  static final int DEGREES_IN_CIRCLE = 360;
  static final int TOLERANCE = 10;
  static final int FLIP_POINT = 360;
  static final int MAX_ANGLE = 225;
  static final int MIN_ANGLE = -135;
  private static final int ENCODER_UNITS = 1023;
  private static final double CONVERSION_RATE = 1;
  static final double ENCODER_TO_ANGLE = DEGREES_IN_CIRCLE / (ENCODER_UNITS * CONVERSION_RATE);
}
