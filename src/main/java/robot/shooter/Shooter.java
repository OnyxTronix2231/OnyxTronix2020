package robot.shooter;

import static robot.RobotConstants.PRIMARY_PID;
import static robot.shooter.ShooterConstants.CLOSE_SOLENOID_VALUE;
import static robot.shooter.ShooterConstants.MAX_FIRST_RANGE_CM;
import static robot.shooter.ShooterConstants.MIN_FIRST_RANGE_CM;
import static robot.shooter.ShooterConstants.MIN_THIRD_RANGE_CM;
import static robot.shooter.ShooterConstants.OPEN_SOLENOID_VALUE;
import static robot.shooter.ShooterConstants.SPEED_FIRST;
import static robot.shooter.ShooterConstants.SPEED_MIDDLE;
import static robot.shooter.ShooterConstants.SPEED_THIRD;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MIN_AMP_FOR_ONE;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_PID_SLOT;
import static robot.shooter.ShooterConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;

  public Shooter(final ShooterComponents components) {
    this.components = components;
  }

  public void shootBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    shootBySpeed(0);
  }

  public boolean isOnTarget() {
    return components.getMasterMotor().getClosedLoopError() < TOLERANCE;
  }

  public void configVelocitySlot() {
    components.getMasterMotor().selectProfileSlot(VELOCITY_PID_SLOT, PRIMARY_PID);
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }

  public void openShooterPiston() {
    components.getDoubleSolenoid().set(OPEN_SOLENOID_VALUE);
  }

  public void closeShooterPiston() {
    components.getDoubleSolenoid().set(CLOSE_SOLENOID_VALUE);
  }

  public int distanceToVelocity(final double distance) {
    if (distance > MIN_THIRD_RANGE_CM) {
      return SPEED_THIRD;
    } else if (distance > MAX_FIRST_RANGE_CM && distance < MIN_THIRD_RANGE_CM) {
      return SPEED_MIDDLE;
    } else if (distance > MIN_FIRST_RANGE_CM) {
      return SPEED_FIRST;
    } else {
      return 0;
    }
  }

  public double getAmp(){
    return components.getMasterMotor().getStatorCurrent();
  }

  public boolean isBallShot(){
    return getAmp() > MIN_AMP_FOR_ONE;
  }
}
