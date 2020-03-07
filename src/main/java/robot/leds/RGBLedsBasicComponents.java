package robot.leds;

import com.ctre.phoenix.CANifier;

public class RGBLedsBasicComponents implements RGBLedsComponents {

  CANifier canifier = new CANifier(0);

  @Override
  public double getBusVoltage() {
    return canifier.getBusVoltage();
  }
}
