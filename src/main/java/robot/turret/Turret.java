package robot.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private final TurretComponents components;

  public Turret(final TurretComponents components) {
    this.components = components;
    initEncoders();
  }

  public void moveBySpeed(final double speed){
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    moveBySpeed(0);
  }
  public void initEncoders(){
    components.getMasterMotor().setSelectedSensorPosition(0);
  }
  public void moveToAngle(final double angle){
    components.getMasterMotor().set(ControlMode.Position, convertAngleToEncoderUnits(angle));
  }
  public int convertAngleToEncoderUnits(final double angle){
    return (int) (TurretConstants.DEGREES_IN_CIRCLE / TurretConstants.ENCODER_UNITS * TurretConstants.CONVERSION_RATE);
  }

}
