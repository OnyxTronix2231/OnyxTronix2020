package robot.roulette;

public final class RouletteConstants {
    public final static int MASTER_MOTOR_PORT = 0;
    public final static int DOUBLE_RIGHT_SOLENOID_REVERSE_CHANNEL = 1;
    public final static int DOUBLE_RIGHT_SOLENOID_FORWARD_CHANNEL = 0;
    public final static int DOUBLE_LEFT_SOLENOID_REVERSE_CHANNEL = 1;
    public final static int DOUBLE_LEFT_SOLENOID_FORWARD_CHANNEL = 0;
    public final static double SPIN_COLOR_SPEED = 0.3;
    public final static double SPIN_ROTATIONS_SPEED = 0.7;
    public final static int COLORS_IN_ROTATIONS = 8;
    public final static int ENCODER_UNITS_PER_ROTATION = 1023;
    public final static int ROULETTE_ROTATION_TO_WHEEL_ROTATION = 2;
    public final static int TOLERANCE = 50;
    public final static int MAX_ROTATIONS = 5;
    public final static int MIN_ROTATIONS = 3;
    public final static double RGB_EQUALS_THRESHOLD = 0.8;
    public final static int AVERAGE_ROTATIONS = (MIN_ROTATIONS + MAX_ROTATIONS) / 2;
    public final static int REQUIRED_ROTATIONS = AVERAGE_ROTATIONS * COLORS_IN_ROTATIONS;
    public final static int PICK_AMP = 40; // TODO: check and change
    public final static int PICK_AMP_DURATION = 1; // TODO: check and change
    public final static int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check and change
    public final static double DISTANCE_FROM_FIELD_SENSOR = 2.0; // TODO: check and change
    public final static RGBValue GREEN_RGB_VALUE = new RGBValue(0.149, 0.599, 0.250);
    public final static RGBValue BLUE_RGB_VALUE = new RGBValue(0.108, 0.428, 0.462);
    public final static RGBValue RED_RGB_VALUE = new RGBValue(0.551, 0.329, 0.119);
    public final static RGBValue YELLOW_RGB_VALUE = new RGBValue(0.319, 0.571, 0.108);
}
