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
    static final double MAX_VELOCITY = 37350; //TODO check and change
    static final double VELOCITY_P = 0.8; //0.15;//TODO check and change
    static final double VELOCITY_I = 0.0;//TODO check and change
    static final double VELOCITY_D = 0.0;//TODO check and change
    static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
  }

  static final DoubleSolenoid.Value OPEN_SOLENOID_VALUE = DoubleSolenoid.Value.kForward;
  static final DoubleSolenoid.Value CLOSE_SOLENOID_VALUE = DoubleSolenoid.Value.kReverse;
  static final double SPEED_FIRST = 35000; //TODO check and change very important meters
  static final double SPEED_MIDDLE = 36000; //TODO check and change very important meters
  static final double SPEED_THIRD = 38000; //TODO check and change very important meters
}
