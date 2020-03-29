package robot.ventilator;

import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.ENCODER_UNITS;

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

  public final double getClosedLoopError(){
    return components.getMasterMotor().getClosedLoopError();
  }

  public final double getAmp() {
    return components.getMasterMotor().getStatorCurrent();
  }

  public final double getRpm(){
    return components.getMasterMotor().getSelectedSensorVelocity() * 600/ ENCODER_UNITS;
  }
}
