package robot.drivetrain;

public final class DriveTrainConstants {

  static final int DRIVE_BY_DISTANCE_SLOT = 0; //TODO change
  static final int VELOCITY_CONTROLLER_PID_SLOT = 2; // TODO: check value
  static final double INCH_TO_CM = 2.54;
  static final double PERIMETER = 6 * INCH_TO_CM * Math.PI; //TODO: tuning is required
  static final double ENCODER_UNITS = 1023.0;
  static final double TOLERANCE = 3; // TODO: tuning is required
  static final double ARB_FEED_FORWARD = 0.04; // TODO: tuning is required
  static final double ARCADE_DRIVE_ROTATION_SENSITIVITY = 0.8; // TODO: check value
  static final double ARCADE_DRIVE_FORWARD_SENSITIVITY = 0.8; // TODO: check value

  public static final class DriveTrainComponentsA {
    static final int LEFT_MASTER_PORT = 2;
    static final int LEFT_SLAVE_PORT = 3;
    static final int RIGHT_MASTER_PORT = 0;
    static final int RIGHT_SLAVE_PORT = 1;
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    static final double MAX_VELOCITY = 800; //TODO: needed to be checked
    static final double DRIVE_BY_DISTANCE_P = 0; //TODO change
    static final double DRIVE_BY_DISTANCE_I = 0; //TODO change
    static final double DRIVE_BY_DISTANCE_D = 0; //TODO change
    static final double CURRENT_LIMIT = 35; // TODO: check value
    static final double TRIGGER_THRESHOLD_CURRENT = 50; // TODO: check value
    static final double TRIGGER_THRESHOLD_TIME = 2.5; // TODO: check value
    static final double PERCENTAGE_CLOSED_LOOP_OUTPUT = 1.0; // TODO: check value
    static final double OPEN_LOOP_RAMP = 0.3; // TODO: check value
    static final double CLOSED_LOOP_RAMP = 0; // TODO: check value
    static final double VELOCITY_CONTROLLER_P = 0; // TODO: check value
    static final double VELOCITY_CONTROLLER_I = 0; // TODO: check value
    static final double VELOCITY_CONTROLLER_D = 0; // TODO: check value
  }
}
