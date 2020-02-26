package robot.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public final class ClimberConstants {

  public static final double UP_SPEED = 0.5; // TODO: check value
  public static final double SET_POINT_ON_BAR = 1; //TODO: check and change
  static final double TOLERANCE = 3; // TODO: tuning is required
  static final double TOLERANCE_IN_ENCODER = 3; // TODO: tuning is required
  static final double ENCODER_UNITS = 1023;
  static final DoubleSolenoid.Value OPEN_SOLENOID_VALUE = DoubleSolenoid.Value.kForward;
  static final DoubleSolenoid.Value CLOSE_SOLENOID_VALUE = DoubleSolenoid.Value.kReverse;

  public static final class ClimberComponentsA {
    static final int MASTER_MOTOR_PORT = 9; // TODO: check value
    static final int SLAVE_MOTOR_PORT = 10; // TODO: check value
    static final int DOUBLE_SOLENOID_FORWARD_PORT = 2; // TODO: check value
    static final int DOUBLE_SOLENOID_REVERSE_PORT = 3; // TODO: check value
    static final double CURRENT_LIMIT = 40; // TODO: check value
    static final double TRIGGER_THRESHOLD_CURRENT = 40; // TODO: check value
    static final double TRIGGER_THRESHOLD_TIME = 0; // TODO: check value
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    static final double MAX_VELOCITY = 0; //TODO check and change
    static final double VELOCITY_KP = 0; //TODO check and change
    static final double VELOCITY_KI = 0; //TODO check and change
    static final double VELOCITY_KD = 0; //TODO check and change
    static final double VELOCITY_KF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    static final double CONVERSION_RATE = 19.8; //TODO check and change
    static final double ARB_FEED_FORWARD = 0; // TODO: tuning is required
    static final double PERIMETER = 0.00286004 * Math.PI; // TODO: Check and change
    static final double ENCODER_TOLERANCE = CONVERSION_RATE * ENCODER_UNITS * TOLERANCE_IN_ENCODER / PERIMETER;
    public static final double WAIT_UNTIL_CLIMB = 0.5; // TODO: Check and change
  }
}
