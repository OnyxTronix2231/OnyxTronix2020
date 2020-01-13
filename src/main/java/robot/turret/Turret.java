package robot.turret;

import static robot.turret.TurretConstants.*;
import static robot.turret.TurretConstants.DEGREES_IN_CIRCLE;

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
    System.out.println(convertAngleToEncoderUnits(angle));
    components.getMasterMotor().set(ControlMode.MotionMagic, convertAngleToEncoderUnits(angle));
  }
  public int convertAngleToEncoderUnits(final double angle){
    return (int) (ENCODER_UNITS * CONVERSION_RATE * (angle / DEGREES_IN_CIRCLE));
  }

  public double getEncoderPosition() {
    return components.getMasterMotor().getSelectedSensorPosition();
  }

}
