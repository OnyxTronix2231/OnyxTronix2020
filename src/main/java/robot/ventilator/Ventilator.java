package robot.ventilator;

import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.CHECKS_PER_SECOND;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.ENCODER_UNITS;
import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.SECONDS_PER_MINUTE;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Ventilator extends SubsystemBase {

  private final VentilatorComponents components;

  public Ventilator(VentilatorComponents components) {
    this.components = components;
  }

  public final void moveVentilatorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotor() {
    moveVentilatorBySpeed(0);
  }

  public final double getStatorCurrent() {
    return components.getMasterMotor().getStatorCurrent();
  }

  public final double getSupplyCurrent() {
    return components.getMasterMotor().getSupplyCurrent();
  }

  public final double getRpm() {
    return components.getMasterMotor().getSelectedSensorVelocity() * CHECKS_PER_SECOND *
        SECONDS_PER_MINUTE / ENCODER_UNITS;
  }

  public final double getEncoderUnitsPerSec() {
    return components.getMasterMotor().getSelectedSensorVelocity() * CHECKS_PER_SECOND;
  }
}
