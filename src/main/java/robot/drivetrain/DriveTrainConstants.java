package robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public final class DriveTrainConstants {

  public static final int DRIVE_BY_DISTANCE_SLOT = 0;
  static final int VELOCITY_CONTROLLER_PID_SLOT = 2;
  static final int CM_TO_METERS = 100;
  static final int SEC_TO_100MS = 10;
  static final double ARCADE_DRIVE_ROTATION_SENSITIVITY = 0.6;
  static final double ARCADE_DRIVE_FORWARD_SENSITIVITY = 0.8;
  static final double CONVERSION_RATE = 9.5;
  static final double TOLERANCE = 3; // TODO: tuning is required
  static final double ARB_FEED_FORWARD = 0.04; // TODO: tuning is required
  static final double INCH_TO_CM = 2.54;
  static final double PERIMETER = 6 * INCH_TO_CM * Math.PI; //TODO: tuning is required
  static final double PERIMETER_IN_METERS = PERIMETER / 100;
  static final Pose2d TARGET_POSE = new Pose2d(6.8, 0, Rotation2d.fromDegrees(180));
  private static final double ENCODER_UNITS = 2048;

  public static final class DriveTrainComponentsA {

    static final int LEFT_MASTER_PORT = 2;
    static final int LEFT_SLAVE_PORT = 3;
    static final int RIGHT_MASTER_PORT = 0;
    static final int RIGHT_SLAVE_PORT = 1;
    static final int MAX_ACCELERATION = 1000; // TODO: Calibration with A
    static final int MAX_VELOCITY = 22000; // TODO: Calibration with A
    static final int PIGEON_PORT = 0;
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    static final double DRIVE_BY_DISTANCE_P = 0; // TODO: Calibration with A
    static final double DRIVE_BY_DISTANCE_I = 0; // TODO: Calibration with A
    static final double DRIVE_BY_DISTANCE_D = 0; // TODO: Calibration with A
    static final double CURRENT_LIMIT = 40; // TODO: check value
    static final double TRIGGER_THRESHOLD_CURRENT = 40; // TODO: check value
    static final double TRIGGER_THRESHOLD_TIME = 0.03; // TODO: check value
    static final double PERCENTAGE_CLOSED_LOOP_OUTPUT = 1.0;
    static final double OPEN_LOOP_RAMP = 0; // TODO: Calibration with A
    static final double CLOSED_LOOP_RAMP = 0;
    static final double VELOCITY_CONTROLLER_P = 0; // TODO: Calibration with A
    static final double VELOCITY_CONTROLLER_I = 0; // TODO: Calibration with A
    static final double VELOCITY_CONTROLLER_D = 0; // TODO: Calibration with A
    static final double MAX_OUTPUT_FORWARD = 1;
    static final double MAX_OUTPUT_REVERSE = -1;
    static final double TRAJECTORY_P = 0.4; // TODO: Calibration with A
    static final double TRAJECTORY_I = 0; // TODO: Calibration with A
    static final double TRAJECTORY_D = 4; // TODO: Calibration with A

    public static final class TrajectoryParams {

      public static final double RAMSETE_B = 2;
      public static final double RAMSETE_ZETA = 0.7;
      public static final double TRAJECTORY_P = 0.5;
      static final double ENCODER_CPR = ENCODER_UNITS * CONVERSION_RATE; // TODO: Calibration with A
      static final double VOLTS = 0.197; // TODO: Calibration with A
      static final double VOLT_SECONDS_PER_METER = 2.1;
      static final double VOLT_SECONDS_SQUARED_PER_METER = 0.452; // TODO: Calibration with A
      static final double MAX_VOLTAGE = 12 * 0.4; // TODO: Calibration with A
      static final double MAX_SPEED_METERS_PER_SECOND = 3; // TODO: Calibration with A
      static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 3; // TODO: Calibration with A
      static final double TRACKWIDTH_METERS = 0.62879; // TODO: Calibration with A
    }

  }
}
