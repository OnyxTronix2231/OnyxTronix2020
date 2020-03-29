package robot.ventilator;

import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.MASTER_MOTOR_PORT;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.MAX_CLOSED_LOOP_OUTPUT;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.MAX_VELOCITY;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.PEAK_AMP;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.PEAK_AMP_DURATION;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.VELOCITY_D;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.VELOCITY_I;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicVentilatorComponents implements VentilatorComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicVentilatorComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.enableCurrentLimit(true);
    masterMotor.setInverted(true);
  }
  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = VELOCITY_P;
    config.slot0.kI = VELOCITY_I;
    config.slot0.kD = VELOCITY_D;
    config.slot0.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    return config;
  }
}
