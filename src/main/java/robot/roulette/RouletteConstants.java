package robot.roulette;

public final class RouletteConstants {

    public static final class RobotAComponents {

        public final static int MASTER_MOTOR_PORT = 11;
        public final static int MAX_ROTATIONS = 5;
        public final static int MIN_ROTATIONS = 3;
        public final static int AVERAGE_ROTATIONS = (MIN_ROTATIONS + MAX_ROTATIONS) / 2;
        public final static int PICK_AMP = 40; // TODO: check and change
        public final static int PICK_AMP_DURATION = 1; // TODO: check and change
        public final static int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check and change
        public final static int DOUBLE_RIGHT_SOLENOID_REVERSE_CHANNEL = 1;
        public final static int DOUBLE_RIGHT_SOLENOID_FORWARD_CHANNEL = 0;
        public final static int DOUBLE_LEFT_SOLENOID_REVERSE_CHANNEL = 2;
        public final static int DOUBLE_LEFT_SOLENOID_FORWARD_CHANNEL = 3;
        public static final int MAX_VELOCITY = 8000;
        public static final int MAX_ACCELERATION = 8000;
        public static final int MOTION_CURVE_STRENGTH = 0;  // TODO: check and change
        public final static double WHEEL_RADIUS = 5;
        public final static double WHEEL_PERIMETER = 2 * Math.PI * WHEEL_RADIUS;
        public final static double ROULETTE_RADIUS = 41;
        public final static double ROULETTE_PERIMETER = 2 * Math.PI * ROULETTE_RADIUS;
        public final static double ROULETTE_ROTATION_TO_WHEEL_ROTATION = ROULETTE_PERIMETER / WHEEL_PERIMETER;
        public static final double VELOCITY_P = 0.1;
        public static final double VELOCITY_I = 0;
        public static final double VELOCITY_D = 0;

    }

    public final static int TOLERANCE = 50;
    public final static int COLORS_IN_ROTATIONS = 8;
    public final static int ENCODER_UNITS_PER_ROTATION = 4000;
    public static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    public final static double SPIN_COLOR_SPEED = 0.3;
    public final static double SPIN_ROTATIONS_SPEED = 0.7;
    public final static double RGB_EQUALS_THRESHOLD = 0.8;
    public final static double DISTANCE_FROM_FIELD_SENSOR = 2.0; // TODO: check and change
    public final static RGBValue GREEN_RGB_VALUE = new RGBValue(0.149, 0.599, 0.250);
    public final static RGBValue BLUE_RGB_VALUE = new RGBValue(0.108, 0.428, 0.462);
    public final static RGBValue RED_RGB_VALUE = new RGBValue(0.551, 0.329, 0.119);
    public final static RGBValue YELLOW_RGB_VALUE = new RGBValue(0.319, 0.571, 0.108);
}
