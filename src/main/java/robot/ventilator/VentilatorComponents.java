package robot.ventilator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface VentilatorComponents {

  WPI_TalonSRX getMasterMotor();
}
