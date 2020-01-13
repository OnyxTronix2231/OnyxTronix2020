package robot.turret;

import static robot.turret.TurretConstants.*;
import static robot.turret.TurretConstants.DEGREES_IN_CIRCLE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;
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

  public void moveToAngle(double angle){
    if(angle > 270) {
      angle -= 360;
    } else if (angle < -270) {
        angle += 360;
    } else if(angle < 0 && angle + 360 < 270 && getAngle() > 0) {
          angle += 360;
    } else if(angle > 0 && angle - 360 > -270 && getAngle() < 0) {
          angle -= 360;
    }
    components.getMasterMotor().set(ControlMode.MotionMagic, angle);
  }

  public double convertAngleToEncoderUnits(final double angle) {
    return angle / ENCODER_TO_ANGLE;
  }

  public double getAngle(){
    return getEncoderPosition() * ENCODER_TO_ANGLE;
  }

  public double getEncoderPosition() {
    return components.getMasterMotor().getSelectedSensorPosition();
  }

}
