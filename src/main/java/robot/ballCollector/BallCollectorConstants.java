package robot.ballCollector;

public final class BallCollectorConstants {

  static final boolean OPEN_SOLENOID = true;
  static final boolean CLOSE_SOLENOID = false;
  static final double PERCENT_OUTPUT = 1; // TODO: check value

  public static final class BallCollectorComponentsA {
    static final int MASTER_MOTOR_PORT = 10;
    static final int SOLENOID_PORT = 0;
    static final int PEAK_AMP = 40; // TODO: check value
    static final int PEAK_AMP_DURATION = 0; // TODO: check value
    static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check value
  }
}
