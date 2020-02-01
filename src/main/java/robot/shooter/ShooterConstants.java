package robot.shooter;

public final class ShooterConstants {

  public static final int MASTER_PORT = 4;
  public static final int SLAVE_PORT = 5;
  public static final double SPEED = 0.5; //TODO check and change
  public static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
  public static final double MAX_VELOCITY = 42000; //TODO check and change
  public static final double VELOCITY_P = 0.15;//TODO check and change
  public static final double VELOCITY_I = 0.0;//TODO check and change
  public static final double VELOCITY_D = 0.0;//TODO check and change
  public static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
  public static final int VELOCITY_PID_SLOT = 0;
  public static final int PRIMARY_PID = 0;
  public static final int AUX_PID = 1;
  public static final int PICK_AMP = 40;//TODO check and change
  public static final int PICK_AMP_DURATION = 10;//TODO check and change
  public static final int CONTINUOUS_CURRENT_LIMIT = 20; // TODO: check and change
  public static final double OPEN_LOOP_RAMP = 1;//TODO: check and change
  public static final double CLOSE_LOOP_RAMP = 1;//TODO: check and change

}
