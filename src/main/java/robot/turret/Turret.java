package robot.turret;

import static robot.turret.TurretConstants.ENCODER_TO_ANGLE;
import static robot.turret.TurretConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.turret.commands.MoveToAngleAndKeep;

import java.util.function.DoubleSupplier;

public class Turret extends SubsystemBase {

  private final TurretComponents components;

  protected Turret(final TurretComponents components) {
    this.components = components;
    initEncoders();
  }

  public void moveBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    moveBySpeed(0);
  }

  public void initEncoders() {
    components.getMasterMotor().setSelectedSensorPosition(0);
  }

  public void moveToAngle(final double angle) {
    double tempAngle = angle;

    if (tempAngle > 270) {
      tempAngle %= 360;
    }

    if (tempAngle < -270) {
      tempAngle %= 360;
    }

    if (tempAngle < 0 && tempAngle + 360 < 270 && getAngle() > 0) {
      tempAngle += 360;
    } else if (tempAngle > 0 && tempAngle - 360 > -270 && getAngle() < 0) {
      tempAngle -= 360;
    }

    components.getMasterMotor().set(ControlMode.MotionMagic, tempAngle);
  }

  public void moveByAngle(final double angle){
    moveToAngle(getAngle() + angle);
  }

  public double convertAngleToEncoderUnits(final double angle) {
    return angle / ENCODER_TO_ANGLE;
  }

  public double getAngle() {
    return getEncoderPosition() * ENCODER_TO_ANGLE;
  }

  public double getEncoderPosition() {
    return components.getMasterMotor().getSelectedSensorPosition();
  }

  public boolean isOnTarget(){
    return Math.abs(components.getMasterMotor().getClosedLoopError()) < TOLERANCE;
  }

}
