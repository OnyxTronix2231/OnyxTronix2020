package robot.loaderConveyor;

public final class LoaderConveyorConstants {

  public static final class LoaderConveyorComponentsA {

    static final int MASTER_MOTOR_PORT = 9;//TODO check and change
    static final double VELOCITY_P = 0;//TODO check and change
    static final double VELOCITY_I = 0;//TODO check and change
    static final double VELOCITY_D = 0;//TODO check and change
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;//TODO check and change
    static final double MAX_VELOCITY = 1500; //TODO check and change
    static final int PEAK_AMP_DURATION = 0; //TODO check and change
    static final int CONTINUOUS_CURRENT_LIMIT = 25; //TODO check and change
    static final int PEAK_AMP = 25; //TODO check and change
    public static final double TIME_TO_MOVE_LOADER = 0.5;
  }

  static final int TOLERANCE = 50;
  static final double PERCENTAGE_OUTPUT = 0.8;
}
