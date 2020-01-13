package robot.drivetrain;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public final class DriveTrainConstants {

  public static final int LEFT_MASTER_PORT = 2;
  public static final int LEFT_SLAVE_PORT = 3;
  public static final int RIGHT_MASTER_PORT = 0;
  public static final int RIGHT_SLAVE_PORT = 1;
  public static final double INCH_TO_CM = 2.54;
  public static final double ENCODER_UNITS = 1023.0;
  public static final double PERIMETER = 6 * INCH_TO_CM * Math.PI; //TODO: tuning is required
  public static final double TOLERANCE = 3; // TODO: tuning is required
  public static final int PRIMARY_PID = 0;
  public static final int DRIVE_BY_DISTANCE_SLOT = 0;
  public static final double ARB_FEED_FORWARD = 0.04; // TODO: tuning is required
  public static final double PERCENTAGE_CLOSED_LOOP_OUTPUT = 1.0;
  public static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
  public static final double MAX_VELOCITY = 800; // TODO: needed to be checked
  public static final double DRIVE_BY_DISTANCE_P = 0;
  public static final double DRIVE_BY_DISTANCE_I = 0;
  public static final double DRIVE_BY_DISTANCE_D = 0;
  public static final int PIGEON_NUMBER = 0;

  public static final class TRAJECTORY_PARAMS {
    // Use Robot Characterization Toolsuite to determine correct values of your robot per the guide found here:
    // https://docs.wpilib.org/en/latest/docs/software/examples-tutorials/trajectory-tutorial/characterizing-drive.html
    // Please do NOT use these values on your robot unless on some cosmic coincidence they are the same.

    public static final int ENCODER_CPR = 1024;
    public static final double WHEEL_DIAMETER_CM = 15;
    public static final double ENCODER_DISTANCE_PER_PULSE =
        (WHEEL_DIAMETER_CM * Math.PI) / (double) ENCODER_CPR;

    // kS value found on the left part of the tool's main screen (Volts used for total movement)
    public static final double VOLTS = 0.22;
    // kV value found on the left part of the tool's main screen (Average amount of volts being used
    // per second by the robot in the specified movement - Voltage Velocity).
    public static final double VOLT_SECONDS_PER_METER = 1.98;
    // kA value found on the left part of the tool's main screen (Speed at which the Volt Velocity rises
    // per second - Voltage Acceleration).
    public static final double VOLT_SECONDS_SQUARED_PER_METER = 0.2;
    // These values are used to calculate the Feedforward of the robot.

    // kP value found on the right part of the tool's screen after following the extra steps as shown
    // in the referenced tutorial above (The amount of voltage the robot uses to correct itself for each
    // meter per second of error calculated - Feedback Gains).
    public static final double P_DRIVE_VEL = 8.5;


    // The maximum distance the robot is able to reach every second (The robot's maximum velocity).
    public static final double MAX_SPEED_METERS_PER_SECOND = 3;
    // The maximum rise in velocity the robot is able to reach every second (The robot's maximum acceleration).
    public static final double KAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 3;


    // Ramsete Parameters - Parameters used for calculations in the Ramsete Controller, a trajectory tracker
    // built into the WPILib libraries.
    // Reasonable baseline values for a Ramsete follower in units of meters and seconds, should work for
    // most robots, provided the previous calculations were correct and metric.
    public static final double RAMSETE_B = 2;
    public static final double RAMSETE_ZETA = 0.7;
    // Horizontal distance between the middle of each of your robot's wheel bases.
    private static final double TRACKWIDTH_METERS = 0.69;
    // This object is used to convert chassis speeds to wheel speeds.
    public static final DifferentialDriveKinematics DRIVE_KINEMATICS =
        new DifferentialDriveKinematics(TRACKWIDTH_METERS);
  }
}
