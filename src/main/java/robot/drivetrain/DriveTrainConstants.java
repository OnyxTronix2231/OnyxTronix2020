package robot.drivetrain;

public final class DriveTrainConstants {

  public static final int LEFT_MASTER_PORT = 2;
  public static final int LEFT_SLAVE_PORT = 3;
  public static final int RIGHT_MASTER_PORT = 0;
  public static final int RIGHT_SLAVE_PORT = 1;
  public static final int PRIMARY_PID = 0;
  public static final int DRIVE_BY_DISTANCE_SLOT = 0;
  public static final double INCH_TO_CM = 2.54;
  public static final double ENCODER_UNITS = 2048;
  public static final double CONVERSION_RATE = 9.5;
  public static final double PERIMETER = 6 * INCH_TO_CM * Math.PI; //TODO: tuning is required
  public static final double TOLERANCE = 5; // TODO: tuning is required
  public static final double ARB_FEED_FORWARD = 0.2; // TODO: tuning is required
  public static final double PERCENTAGE_CLOSED_LOOP_OUTPUT = 1.0;
  public static final double MAX_CLOSED_LOOP_OUTPUT = 4096;
  public static final double MAX_VELOCITY = 15000; // TODO: needed to be checked
  public static final double DRIVE_BY_DISTANCE_P = 0;
  public static final double DRIVE_BY_DISTANCE_I = 0;
  public static final double DRIVE_BY_DISTANCE_D = 0;
  public static final double CURRENT_LIMIT = 35; // TODO: check value
  public static final double TRIGGER_THRESHOLD_CURRENT = 50; // TODO: check value
  public static final double TRIGGER_THRESHOLD_TIME = 2.5; // TODO: check value
}
