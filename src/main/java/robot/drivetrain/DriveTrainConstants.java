package robot.drivetrain;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public final class DriveTrainConstants {

  static final class DriveTrainComponents {

    static double ODOMETRY_TARGET_X = 0;
    static double ODOMETRY_TARGET_Y = 0;
    static double ODOMETRY_TARGET_ANGLE = 0;
    static final int LEFT_MASTER_PORT = 2;
    static final int LEFT_SLAVE_PORT = 3;
    static final int RIGHT_MASTER_PORT = 0;
    static final int RIGHT_SLAVE_PORT = 1;
    static final int MAX_ACCELERATION = 1000; // TODO: needed to be checked
    static final int MAX_VELOCITY = 13400; // TODO: needed to be checked
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    static final double DRIVE_BY_DISTANCE_P = 0; //TODO change
    static final double DRIVE_BY_DISTANCE_I = 0; //TODO change
    static final double DRIVE_BY_DISTANCE_D = 0; //TODO change
    static final double CURRENT_LIMIT = 35; // TODO: check value
    static final double TRIGGER_THRESHOLD_CURRENT = 50; // TODO: check value
    static final double TRIGGER_THRESHOLD_TIME = 2.5; // TODO: check value
    static final double PERCENTAGE_CLOSED_LOOP_OUTPUT = 1.0;
    static final double OPEN_LOOP_RAMP = 0.3;
    static final double CLOSED_LOOP_RAMP = 0;
    static final double VELOCITY_CONTROLLER_P = 0;
    static final double VELOCITY_CONTROLLER_I = 0;
    static final double VELOCITY_CONTROLLER_D = 0;
    static final double MAX_OUTPUT_FORWARD = 0.9;
    static final double MAX_OUTPUT_REVERSE = 0.9;
    public static final double TRAJECTORY_P = 0.4;
    public static final double TRAJECTORY_I = 0;
    public static final double TRAJECTORY_D = 4;
  }

  public static final class TRAJECTORY_PARAMS {
    public static final int TRAJECTORY_PID_SLOT = 1;
    public static final double ENCODER_CPR = ENCODER_UNITS * 9.5;
    public static final double VOLTS = 0.365;
    public static final double VOLT_SECONDS_PER_METER = 0;
    public static final double VOLT_SECONDS_SQUARED_PER_METER = 0.333;
    public static final double MAX_VOLTAGE = 10;
    public static final double MAX_SPEED_METERS_PER_SECOND = 3;
    public static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 3;
    public static final double TRACKWIDTH_METERS = 0.679;
    public static final int DEGREES_IN_FULL_ROTATION = 360;
    public static final double RAMSETE_B = 2;
    public static final double RAMSETE_ZETA = 0.7;
    public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(TRACKWIDTH_METERS);
    public static final SimpleMotorFeedforward FEED_FORWARD =
        new SimpleMotorFeedforward(VOLTS, VOLT_SECONDS_PER_METER, VOLT_SECONDS_SQUARED_PER_METER);
  }

  static final int PRIMARY_PID = 0; //TODO change
  static final int DRIVE_BY_DISTANCE_SLOT = 0; //TODO change
  static final int VELOCITY_CONTROLLER_PID_SLOT = 2;
  static final int CM_TO_METERS = 100;
  static final int SEC_TO_100MS= 10;
  static final double CONVERSION_RATE = 9.5;
  static final double INCH_TO_CM = 2.54;
  static final double PERIMETER = 6 * INCH_TO_CM * Math.PI; //TODO: tuning is required
  static final double PERIMETER_IN_METERS = PERIMETER / 100;
  static final double ENCODER_UNITS = 1023.0;
  static final double TOLERANCE = 3; // TODO: tuning is required
  static final double ARB_FEED_FORWARD = 0.04; // TODO: tuning is required
  static final double ARCADE_DRIVE_ROTATION_SENSITIVITY = 0.8;
  static final double ARCADE_DRIVE_FORWARD_SENSITIVITY = 0.8;

}
