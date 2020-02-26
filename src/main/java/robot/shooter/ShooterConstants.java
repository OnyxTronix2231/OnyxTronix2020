package robot.shooter;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public final class ShooterConstants {

  public static final class ShooterComponentsA {
    public static final double MAX_VELOCITY = 37350;
    static final int MASTER_PORT = 7;
    static final int SLAVE_PORT = 8;
    static final int SOLENOID_PORT = 1;
    static final int PEAK_AMP = 40;//TODO check and change
    static final int PEAK_AMP_DURATION = 100;//TODO check and change
    static final int CONTINUOUS_CURRENT_LIMIT = 30; // TODO: check and change
    static final int MIDDLE_DISTANCE = 450;
    static final int VELOCITY_PID_SLOT = 0;
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    static final double VELOCITY_P = 0.7;
    static final double VELOCITY_I = 0;
    static final double VELOCITY_D = 56;
    static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    static final double OPEN_LOOP_RAMP = 0;//TODO: check and change
    static final double CLOSE_LOOP_RAMP = 0;//TODO: check and change
  }

  static final boolean IS_PISTON_OPEN = false;
  static final int PERCENT_OUT_PUT = 1; //TODO check and change very important meters
  static final int TOLERANCE = 300;
  static final DoubleSolenoid.Value OPEN_SOLENOID_VALUE = DoubleSolenoid.Value.kForward;
  static final DoubleSolenoid.Value CLOSE_SOLENOID_VALUE = DoubleSolenoid.Value.kReverse;
}
