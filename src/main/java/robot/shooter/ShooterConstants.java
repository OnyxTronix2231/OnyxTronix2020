package robot.shooter;

public final class ShooterConstants {

  public static final class ShooterComponents {
    static final int MASTER_PORT = 4;//TODO check and change
    static final int SLAVE_PORT = 5;//TODO check and change
    static final int PICK_AMP = 40;//TODO check and change
    static final int PICK_AMP_DURATION = 10;//TODO check and change
    static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check and change
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    static final double MAX_VELOCITY = 39400; //TODO check and change
    static final double VELOCITY_P = 0.12; //0.15;//TODO check and change
    static final double VELOCITY_I = 0.0;//TODO check and change
    static final double VELOCITY_D = 7;//TODO check and change
    static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    static final int VELOCITY_PID_SLOT = 0;
    static final int PRIMARY_PID = 0;
    static final int AUX_PID = 1;
    static final double OPEN_LOOP_RAMP = 1;//TODO: check and change
    static final double CLOSE_LOOP_RAMP = 1;//TODO: check and change
  }
  static final int MAX_FIRST_RANGE_CM = 550; //TODO check and change very important meters
  static final int MIN_FIRST_RANGE_CM = 250; //TODO check and change very important meters
  static final int MIN_THIRD_RANGE_CM = 750; //TODO check and change very important meters
  static final double SPEED_FIRST = 35000; //TODO check and change very important meters
  static final double SPEED_MIDDLE = 36000; //TODO check and change very important meters
  static final double SPEED_THIRD = 38000; //TODO check and change very important meters
  static final int MAX_OUT_PUT_PERCENT = 1; //TODO check and change very important meters
}
