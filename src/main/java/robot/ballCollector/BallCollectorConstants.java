package robot.ballCollector;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public final class BallCollectorConstants {

  public static final class BallCollectorComponentsA {
    static final int MASTER_MOTOR_PORT = 6;
    static final int DOUBLE_SOLENOID_FORWARD_PORT = 0; //TODO check and change to all of the ports
    static final int DOUBLE_SOLENOID_REVERSE_PORT = 1; //TODO check and change to all of the ports
    static final int PEAK_AMP = 40; // TODO: check value
    static final int PEAK_AMP_DURATION = 0; // TODO: check value
    static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check value
  }

  static final DoubleSolenoid.Value OPEN_SOLENOID_VALUE = DoubleSolenoid.Value.kForward;
  static final DoubleSolenoid.Value CLOSE_SOLENOID_VALUE = DoubleSolenoid.Value.kReverse;
  static final double SPEED = 0.5; // TODO: check value
    public static final double MIN_AMP_FOR_ONE = 39.199;
}
