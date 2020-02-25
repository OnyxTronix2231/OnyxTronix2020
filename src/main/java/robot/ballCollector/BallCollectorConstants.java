package robot.ballCollector;

public final class BallCollectorConstants {

  public static final double PERCENT_OUTPUT = 1;
  public static final double REVERSE_OUTPUT = -1;
  public static final double DURING_CLOSED_PERCENT_OUTPUT = 0.3;
  public static final double CLOSING_SEQUENCE_DELAY = 0.75;
  public static final double CLOSING_SEQUENCE_TIMEOUT = 0.25;
  static final boolean OPEN_SOLENOID = true;
  static final boolean CLOSE_SOLENOID = false;

  public static final class BallCollectorComponentsA {
    static final int MASTER_MOTOR_PORT = 10;
    static final int SOLENOID_PORT = 0;
    static final int PEAK_AMP = 40; // TODO: check value
    static final int PEAK_AMP_DURATION = 0; // TODO: check value
    static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check value
  }
}
