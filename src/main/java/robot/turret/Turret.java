package robot.turret;

import static robot.turret.TurretConstants.DEGREES_IN_CIRCLE;
import static robot.turret.TurretConstants.ENCODER_TO_ANGLE;
import static robot.turret.TurretConstants.FLIP_POINT;
import static robot.turret.TurretConstants.MAX_ANGLE;
import static robot.turret.TurretConstants.MIN_ANGLE;
import static robot.turret.TurretConstants.TurretComponentsA.VELOCITY_D;
import static robot.turret.TurretConstants.TurretComponentsA.VELOCITY_F;
import static robot.turret.TurretConstants.TurretComponentsA.VELOCITY_I;
import static robot.turret.TurretConstants.TurretComponentsA.VELOCITY_P;
import static robot.turret.TurretConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private double angleOffset;
  private final TurretComponents components;

  protected Turret(final TurretComponents components) {
    this.components = components;
    angleOffset = 0;
    ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Turret");

    shuffleboardTab.add("Velocity P", VELOCITY_P).getEntry().
        addListener(p -> components.getMasterMotor().config_kP(0, p.value.getDouble()), EntryListenerFlags.kUpdate);

    shuffleboardTab.add("Velocity I", VELOCITY_I).getEntry().
        addListener(i -> components.getMasterMotor().config_kP(0, i.value.getDouble()), EntryListenerFlags.kUpdate);

    shuffleboardTab.add("Velocity D", VELOCITY_D).getEntry().
        addListener(d -> components.getMasterMotor().config_kP(0, d.value.getDouble()), EntryListenerFlags.kUpdate);

    shuffleboardTab.add("Velocity F", VELOCITY_F).getEntry().
        addListener(f -> components.getMasterMotor().config_kF(0, f.value.getDouble()), EntryListenerFlags.kUpdate);

    shuffleboardTab.addNumber("Motion magic error: ", () -> components.getMasterMotor().getClosedLoopError());
  }

  public void moveBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    moveBySpeed(0);
  }

  public void moveToAngle(final double angle) {
    double tempAngle = angle;
    if (tempAngle < -FLIP_POINT && getAngleRTR() == MIN_ANGLE) {
      tempAngle = MAX_ANGLE;
    } else if (tempAngle > FLIP_POINT && getAngleRTR() == MAX_ANGLE) {
      tempAngle = MIN_ANGLE;
    } else if (tempAngle > MAX_ANGLE && tempAngle < DEGREES_IN_CIRCLE) {
      tempAngle -= DEGREES_IN_CIRCLE;
    } else if (tempAngle < MIN_ANGLE && tempAngle > -DEGREES_IN_CIRCLE) {
      tempAngle += DEGREES_IN_CIRCLE;
    }

    components.getMasterMotor().set(ControlMode.MotionMagic, convertAngleToEncoderUnits(tempAngle));
  }

  public void setOffset(final double offset) {
    angleOffset += offset;
  }

  public double getOffset() {
    return angleOffset;
  }

  public void zeroOffset() {
    angleOffset = 0;
  }

  public void moveByAngle(final double angle) {
    moveToAngle(getAngleRTR() + angle);
  }

  public double convertAngleToEncoderUnits(final double angle) {
    return angle / ENCODER_TO_ANGLE;
  }

  public double getAngleRTR() {
    return getEncoderPosition() * ENCODER_TO_ANGLE % DEGREES_IN_CIRCLE;
  }

  public double getEncoderPosition() {
    return components.getMasterMotor().getSelectedSensorPosition();
  }

  public boolean isOnTarget() {
    return Math.abs(components.getMasterMotor().getClosedLoopError()) < TOLERANCE;
  }
}
