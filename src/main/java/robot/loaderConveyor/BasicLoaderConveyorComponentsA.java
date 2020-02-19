package robot.loaderConveyor;

import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.MASTER_MOTOR_PORT;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.MAX_CLOSED_LOOP_OUTPUT;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.MAX_VELOCITY;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.PEAK_AMP;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.PEAK_AMP_DURATION;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.VELOCITY_D;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.VELOCITY_I;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class BasicLoaderConveyorComponentsA implements LoaderConveyorComponents {

  private final WPI_VictorSPX masterMotor;

  public BasicLoaderConveyorComponentsA() {
    masterMotor = new WPI_VictorSPX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public WPI_VictorSPX getMasterMotor() {
    return masterMotor;
  }

}
