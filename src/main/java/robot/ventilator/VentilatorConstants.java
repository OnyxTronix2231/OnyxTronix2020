package robot.ventilator;

public class VentilatorConstants {

  public static final class VentilatorComponentsA {

    static final int MASTER_MOTOR_PORT = 9;//TODO check and change
    static final double VELOCITY_P = 0;//TODO check and change
    static final double VELOCITY_I = 0;//TODO check and change
    static final double VELOCITY_D = 0;//TODO check and change
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;//TODO check and change
    static final double ENCODER_UNITS = 4096;//TODO check and change
    static final double MAX_VELOCITY = 1500; //TODO check and change
    static final int PEAK_AMP_DURATION = 0; //TODO check and change
    static final int CONTINUOUS_CURRENT_LIMIT = 40; //TODO check and change
    static final double DISTANCE_WITH_BALL = 180; // TODO: check and change
    static final int PEAK_AMP = 40; //TODO check and change
  }
}
