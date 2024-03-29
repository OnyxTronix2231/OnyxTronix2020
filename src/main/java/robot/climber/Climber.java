package robot.climber;

import static robot.RobotConstants.PRIMARY_PID;
import static robot.climber.ClimberConstants.CLOSE_SOLENOID_VALUE;
import static robot.climber.ClimberConstants.ClimberComponentsA.*;
import static robot.climber.ClimberConstants.ClimberComponentsA.ARB_FEED_FORWARD;
import static robot.climber.ClimberConstants.ClimberComponentsA.CONVERSION_RATE;
import static robot.climber.ClimberConstants.ClimberComponentsA.PERIMETER;
import static robot.climber.ClimberConstants.ENCODER_UNITS;
import static robot.climber.ClimberConstants.OPEN_SOLENOID_VALUE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  private final ClimberComponents components;

  public Climber(final ClimberComponents components) {
    this.components = components;
  }

  public void climbBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void initMotionProfileSlot(final int slot) {
    components.getMasterMotor().selectProfileSlot(slot, PRIMARY_PID);
  }

  public void stopMotor() {
    climbBySpeed(0);
  }

  public void closePistons() {
    components.getDoubleSolenoid().set(CLOSE_SOLENOID_VALUE);
  }

  public void openPistons() {
    components.getDoubleSolenoid().set(OPEN_SOLENOID_VALUE);
  }

  public double getTargetFromDistance(final double distance) {
    return cmToEncoderUnits(distance) + components.getMasterMotor().getSelectedSensorPosition();
  }

  public void climbByMotionMagic(final double target) {
    components.getMasterMotor().set(ControlMode.MotionMagic, target, DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
  }


  public boolean isClimberOnTarget(final double target) {
    return Math.abs(target - components.getMasterMotor().getSelectedSensorPosition()) <
        ENCODER_TOLERANCE;
  }

  private double cmToEncoderUnits(final double cm) {
    return CONVERSION_RATE * ENCODER_UNITS * cm / PERIMETER;
  }

  public void setNeutralModeToBrake() {
    components.getMasterMotor().setNeutralMode(NeutralMode.Brake);
    components.getSlaveMotor().setNeutralMode(NeutralMode.Brake);
  }

  public void setNeutralModeToCoast() {
    components.getMasterMotor().setNeutralMode(NeutralMode.Coast);
    components.getSlaveMotor().setNeutralMode(NeutralMode.Coast);
  }
}
