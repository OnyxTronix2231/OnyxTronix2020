package robot.drivetrain;

public final class DriveTrainConstants {

  public static final int MIN_CHECKS = 10;
  public static final double ENCODER_UNITS = 1023.0;
  public static final double PERIMETER = 7.5 * 2 * Math.PI; //TODO: tuning is required
  public static final double TOLERANCE = 3; // TODO tuning is required
  public static final int PRIMARY_PID = 0;
  public static final int DRIVE_BY_DISTANCE_SLOT = 0;
  public static final double ARB_FEED_FORWARD = 0.04; // TODO tuning is required
  public static final double PERCENTAGE_CLOSED_LOOP_OUTPUT = 1.0;
  public static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
  public static final double MAX_VELOCITY = 800; // TODO needed to be checked
  public static final double DRIVE_BY_DISTANCE_P = 0;
  public static final double DRIVE_BY_DISTANCE_I = 0;
  public static final double DRIVE_BY_DISTANCE_D = 0;
}