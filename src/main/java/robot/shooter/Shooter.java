package robot.shooter;

import static robot.RobotConstants.PRIMARY_PID;
import static robot.shooter.ShooterConstants.IS_PISTON_OPEN;
import static robot.shooter.ShooterConstants.ShooterComponentsA.AT_SHOOTING_VELOCITY;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MIDDLE_DISTANCE;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MIN_VELOCITY_ERROR;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_PID_SLOT;
import static robot.shooter.ShooterConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;
  private double lastVelocityError;

  public Shooter(final ShooterComponents components) {
    this.components = components;
    Shuffleboard.getTab("Shooter").addNumber("PID Error",
        () -> components.getLeftMasterMotor().getClosedLoopError());
    Shuffleboard.getTab("Shooter").addNumber("Current velocity",
        () -> components.getLeftMasterMotor().getSelectedSensorVelocity());
    lastVelocityError = 0;
  }

  public void shootBySpeed(final double speed) {
    components.getLeftMasterMotor().set(speed);
  }

  public void stopMotor() {
    shootBySpeed(0);
  }

  public boolean isOnTarget() {
    return Math.abs(components.getLeftMasterMotor().getClosedLoopError()) < TOLERANCE;
  }

  public void configVelocitySlot() {
    components.getLeftMasterMotor().selectProfileSlot(VELOCITY_PID_SLOT, PRIMARY_PID);
  }

  public void setVelocity(final double velocity) {
    components.getLeftMasterMotor().set(ControlMode.Velocity, velocity);
  }

  public void openShooterPiston() {
    components.getSolenoid().set(IS_PISTON_OPEN);
  }

  public void closeShooterPiston() {
    components.getSolenoid().set(!IS_PISTON_OPEN);
  }

  public double distanceToVelocity(double distance) {
    if (distance > MIDDLE_DISTANCE) {
      return -0.0121 * Math.pow(distance, 2) + 26.707 * distance + 24130;
    }
    return 0.1912 * Math.pow(distance, 2) - 161.44 * distance +67791;
  }

  // y= -0.0121x2 +26.707x + 24130 > 450
  //y = 0.1912x2 - 161.44x +67791 < 450


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
