package robot.drivetrain;

public final class DriveTrainConstants {

    public static final int MIN_CHECKS = 10;
    public static final double ENCODER_UNITES = 1023.0;
    public static final double RADIUS = 7.5; //TODO: tuning is required
    public static final double TOLERANCE = 3;
    public static final int PRIMARY_PID = 0;
    public static final int MOTION_MAGIC_PID_SLOT = 0;
    public static final double ARB_FEED_FORWARD = 0.04; // TODO tuning is required
    public static final double PERCENTAGE_CLOSED_LOOP_OUTPUT = 1.0;
    public static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    public static final double MAX_VELOCITY = 800; // TODO needed to be checked


}
