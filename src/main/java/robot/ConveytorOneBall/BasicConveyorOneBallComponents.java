package robot.ConveytorOneBall;

import static robot.ConveytorOneBall.ConveyorOneBallConstants.MASTER_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicConveyorOneBallComponents implements ConveyorOneBallComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicConveyorOneBallComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();

  }

    @Override
    public WPI_TalonSRX getMasterMotor() {
     return masterMotor;
    }

  }

