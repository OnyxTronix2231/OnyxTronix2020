package robot.turret;

import static robot.turret.TurretConstants.*;
import static robot.turret.TurretConstants.ENCODER_TO_ANGLE;
import static robot.turret.TurretConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.turret.commands.MoveToAngleAndKeep;

import java.util.function.DoubleSupplier;

public class Turret extends SubsystemBase {

  private final TurretComponents components;
  protected final ShuffleboardTab shuffleboardTab;
  private double angleOffset;

  protected Turret(final TurretComponents components) {
    this.components = components;
    initEncoders();
    angleOffset = 0;
    shuffleboardTab = Shuffleboard.getTab("Turret");
    shuffleboardTab.add("Velocity F",VELOCITY_F).getEntry().
        addListener(f -> components.getMasterMotor().config_kF(0, f.value.getDouble()),EntryListenerFlags.kUpdate);

    shuffleboardTab.add("Velocity P",VELOCITY_P).getEntry().
        addListener(p -> components.getMasterMotor().config_kP(0, p.value.getDouble()), EntryListenerFlags.kUpdate);

    shuffleboardTab.add("Velocity D",VELOCITY_D).getEntry().
        addListener(d -> components.getMasterMotor().config_kP(0, d.value.getDouble()), EntryListenerFlags.kUpdate);

    shuffleboardTab.add("Velocity I",VELOCITY_I).getEntry().
        addListener(i -> components.getMasterMotor().config_kP(0, i.value.getDouble()),EntryListenerFlags.kUpdate);
    shuffleboardTab.addNumber("Motion magic error: ", () -> components.getMasterMotor().getClosedLoopError());
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
    if(tempAngle < -135) {
      tempAngle = 135;
    } else if(tempAngle > 135) {
      tempAngle = -135;
    }

//    if(lastAngle != tempAngle){
//      lastAngle = tempAngle;
//    }

    components.getMasterMotor().set(ControlMode.MotionMagic, tempAngle / ENCODER_TO_ANGLE);
  }

  public void setOffsetByPercent(double percent) {
    angleOffset += percent * 1;
  }

  public double getOffsetByPercent() {
    return angleOffset;
  }

  public void zeroOffsetByPercent() {
    angleOffset = 0;
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
