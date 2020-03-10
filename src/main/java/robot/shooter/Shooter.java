package robot.shooter;

import static robot.RobotConstants.PRIMARY_PID;
import static robot.shooter.ShooterConstants.IS_PISTON_OPEN;
import static robot.shooter.ShooterConstants.ShooterComponentsA.AT_SHOOTING_VELOCITY;
import static robot.shooter.ShooterConstants.ShooterComponentsA.LAST_DISTANCE_VELOCITY;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MAX_LAST_DISTANCE;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MIDDLE_DISTANCE;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MIN_LAST_DISTANCE;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MIN_VELOCITY_ERROR;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_PID_SLOT;
import static robot.shooter.ShooterConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;
  private double lastVelocityError;

  public Shooter(final ShooterComponents components) {
    this.components = components;
    lastVelocityError = Integer.MAX_VALUE;
    Shuffleboard.getTab("Shooter").addNumber("PID Error",
        () -> components.getMasterMotor().getClosedLoopError());
    Shuffleboard.getTab("Shooter").addNumber("Current RPM",
        () -> components.getMasterMotor().getSelectedSensorVelocity() * 600 / 2046.0);
    Shuffleboard.getTab("Shooter").addNumber("Current velocity",
        () -> components.getMasterMotor().getSelectedSensorVelocity());
  }

  public void shootBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    shootBySpeed(0);
  }

  public boolean isOnTarget() {
    return Math.abs(components.getMasterMotor().getClosedLoopError()) < TOLERANCE;
  }

  public void configVelocitySlot() {
    components.getMasterMotor().selectProfileSlot(VELOCITY_PID_SLOT, PRIMARY_PID);
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }

  public void openShooterPiston() {
    components.getSolenoid().set(IS_PISTON_OPEN);
  }

  public void closeShooterPiston() {
    components.getSolenoid().set(!IS_PISTON_OPEN);
  }

  public double distanceToVelocity(double distance) {
    if (distance < MIDDLE_DISTANCE) {
      return 632637 * Math.pow(distance, -0.584);
    } else if (distance < MIN_LAST_DISTANCE) {
      return 2654.4 * Math.pow(distance, 0.2844);
    } else if (distance < MAX_LAST_DISTANCE){
      return LAST_DISTANCE_VELOCITY;
    }
    return -0.2 * Math.pow(distance, 2) + 325 * distance - 114250;
  }
// y = 632637 * Math.pow(distance, -0.584); 400 - 550
// y = 2654.4 * Math.pow(distance, 0.2844); 550 - 675
// y = 17000; 675 - 750
// y = -0.2 * Math.pow(distance, 2) + 325 * distance - 114250; 750 - 800



  public void startChecking() {
    lastVelocityError = Integer.MAX_VALUE;
  }

  public double getVelocityError(){
    return components.getMasterMotor().getClosedLoopError();
  }

  public boolean isBallShot(){
    if (getVelocityError() > MIN_VELOCITY_ERROR && getVelocityError() > lastVelocityError) {
      lastVelocityError = getVelocityError();
      return true;
    }
    return false;
  }

  public boolean isBallNotShot() {
    return !isBallShot();
  }

  public boolean isReadyToShoot() {
    return getVelocityError() < AT_SHOOTING_VELOCITY;
  }
}
