package robot.ballCollector;

public final class BallCollectorConstants {

  public static final double PERCENT_OUTPUT = 1;
  public static final double MIN_AMP_FOR_ONE = 14;
  public static final double REVERSE_OUTPUT = -1;
  public static final double DURING_CLOSED_PERCENT_OUTPUT = 0.3;
  public static final double CLOSING_SEQUENCE_DELAY = 0.3;
  public static final double CLOSING_SEQUENCE_TIMEOUT = 0.2;
  static final boolean OPEN_SOLENOID = true;
  static final boolean CLOSE_SOLENOID = false;

  public static final class BallCollectorComponentsA {
    static final int MASTER_MOTOR_PORT = 10;
    static final int SOLENOID_PORT = 3;
    static final int PEAK_AMP = 20; // TODO: check value
    static final int PEAK_AMP_DURATION = 0; // TODO: check value
    static final double BALL_COLLECTOR_RAMP = 1;
    static final int CONTINUOUS_CURRENT_LIMIT = 35; // TODO: check value
  }
}
