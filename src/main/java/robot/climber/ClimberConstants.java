package robot.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public final class ClimberConstants {

  static final DoubleSolenoid.Value OPEN_SOLENOID_VALUE = DoubleSolenoid.Value.kForward;
  static final DoubleSolenoid.Value CLOSE_SOLENOID_VALUE = DoubleSolenoid.Value.kReverse;
  static final double UP_SPEED = 0.5; // TODO: check value
  static final double DOWN_SPEED = 0.5; // TODO: check value

  public static final class ClimberComponentsA {
    static final int MASTER_MOTOR_PORT = 9; // TODO: check value
    static final int SLAVE_MOTOR_PORT = 10; // TODO: check value
    static final int DOUBLE_SOLENOID_FORWARD_PORT = 2; // TODO: check value
    static final int DOUBLE_SOLENOID_REVERSE_PORT = 3; // TODO: check value
    static final double CURRENT_LIMIT = 30; // TODO: check value
    static final double TRIGGER_THRESHOLD_CURRENT = 50; // TODO: check value
    static final double TRIGGER_THRESHOLD_TIME = 2.5; // TODO: check value
  }
}
