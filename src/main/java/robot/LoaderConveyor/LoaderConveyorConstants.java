package robot.LoaderConveyor;

public class LoaderConveyorConstants {

  public static final class LoaderConveyorComponents {

    static final int MASTER_MOTOR_PORT = 9;
    static final int SLAVE_MOTOR_PORT = 12;
    static final double LOADER_SPEED = 1; //TODO check and change
    static final double VELOCITY_P = 0;//TODO check and change
    static final double VELOCITY_I = 0;//TODO check and change
    static final double VELOCITY_D = 0;//TODO check and change
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;//TODO check and change
    static final double MAX_VELOCITY = 1500; //TODO check and change
    static final int PICK_AMP_DURATION = 0; //TODO check and change
    static final int CONTINUOUS_CURRENT_LIMIT = 40; //TODO check and change
    static final int PICK_AMP = 40; //TODO check and change
  }
}
