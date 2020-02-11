package robot.ballCollector;

public final class BallCollectorConstants {

    public static final class RobotAComponents {
        public static final int MASTER_MOTOR_PORT = 6;
        public static final int DOUBLE_SOLENOID_FORWARD_PORT = 0;
        public static final int DOUBLE_SOLENOID_REVERSE_PORT = 1;
        public static final int PICK_AMP = 40; // TODO: check value
        public static final int PICK_AMP_DURATION = 0; // TODO: check value
        public static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check value
    }

    public static final double MIN_AMP_FOR_ONE = 39.199;
}
