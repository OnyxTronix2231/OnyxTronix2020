package robot.turret;

import static robot.turret.TurretConstants.DEGREES_IN_CIRCLE;
import static robot.turret.TurretConstants.ENCODER_TO_ANGLE;
import static robot.turret.TurretConstants.FLIP_POINT;
import static robot.turret.TurretConstants.MAX_ANGLE;
import static robot.turret.TurretConstants.MIN_ANGLE;
import static robot.turret.TurretConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private final TurretComponents components;
  private final int absoluteEncoderOffset;

  public Turret(final TurretComponents components) {
    this.components = components;
    absoluteEncoderOffset = components.getAbsoluteEncoderOffset();
    components.getMasterMotor().configForwardSoftLimitThreshold(convertAngleToEncoderUnits(MAX_ANGLE));
    components.getMasterMotor().configReverseSoftLimitThreshold(convertAngleToEncoderUnits(MIN_ANGLE));
    components.getMasterMotor().configForwardSoftLimitEnable(true);
    components.getMasterMotor().configReverseSoftLimitEnable(true);
  }

  public void initMoveMotionMagic() {
  }

  public void moveTurretBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    moveTurretBySpeed(0);
  }

  public void moveToAngle(final double angle) {
    double tempAngle = angle;
    if (tempAngle < -FLIP_POINT) {
      tempAngle = MAX_ANGLE;
    } else if (tempAngle > FLIP_POINT) {
      tempAngle = MIN_ANGLE;
    } else if (tempAngle > DEGREES_IN_CIRCLE + MIN_ANGLE) {
      tempAngle -= DEGREES_IN_CIRCLE;
    } else if (tempAngle < -DEGREES_IN_CIRCLE + MAX_ANGLE) {
      tempAngle += DEGREES_IN_CIRCLE;
    } else if (tempAngle > MAX_ANGLE) {
      tempAngle = MAX_ANGLE;
    } else if (tempAngle < MIN_ANGLE) {
      tempAngle = MIN_ANGLE;
    }

    components.getMasterMotor().set(ControlMode.MotionMagic, convertAngleToEncoderUnits(tempAngle));
  }

  public int convertAngleToEncoderUnits(final double angle) {
    return (int) (angle / ENCODER_TO_ANGLE + absoluteEncoderOffset);
  }

  public double getAngleRTR() {
    return (getEncoderPosition() - absoluteEncoderOffset) * ENCODER_TO_ANGLE % DEGREES_IN_CIRCLE;
  }

  public double getEncoderPosition() {
    return components.getMasterMotor().getSelectedSensorPosition();
  }

  public boolean isOnTarget() {
    return Math.abs(components.getMasterMotor().getClosedLoopError()) < TOLERANCE;
  }
}
