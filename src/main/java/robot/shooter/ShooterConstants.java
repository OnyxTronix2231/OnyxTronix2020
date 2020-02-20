package robot.shooter;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public final class ShooterConstants {

  public static final class ShooterComponentsA {
    static final int MASTER_PORT = 7;
    static final int SLAVE_PORT = 8;
    static final int DOUBLE_SOLENOID_FORWARD_PORT = 3;//TODO check ang change!!!!!
    static final int DOUBLE_SOLENOID_REVERSE_PORT = 4;//TODO check ang change!!!!!
    static final int PEAK_AMP = 40;//TODO check and change
    static final int PEAK_AMP_DURATION = 10;//TODO check and change
    static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check and change
    static final int VELOCITY_PID_SLOT = 0;
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    static final double MAX_VELOCITY = 37000; //TODO check and change
    static final double VELOCITY_P = 0.2;
    static final double VELOCITY_I = 0.0;
    static final double VELOCITY_D = 0.5;
    static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    static final double OPEN_LOOP_RAMP = 1;//TODO: check and change
    static final double CLOSE_LOOP_RAMP = 0;//TODO: check and change
  }

  static final int MAX_FIRST_RANGE_CM = 550; //TODO check and change very important meters
  static final int MIN_FIRST_RANGE_CM = 250; //TODO check and change very important meters
  static final int MIN_THIRD_RANGE_CM = 750; //TODO check and change very important meters
  static final int PERCENT_OUT_PUT = 1; //TODO check and change very important meters
  static final DoubleSolenoid.Value OPEN_SOLENOID_VALUE = DoubleSolenoid.Value.kForward;
  static final DoubleSolenoid.Value CLOSE_SOLENOID_VALUE = DoubleSolenoid.Value.kReverse;
  static final double SPEED_FIRST = 35000; //TODO check and change very important meters
  static final double SPEED_MIDDLE = 36000; //TODO check and change very important meters
  static final double SPEED_THIRD = 38000; //TODO check and change very important meters
}
