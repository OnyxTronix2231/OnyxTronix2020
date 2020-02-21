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
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_PID_SLOT;
import static robot.shooter.ShooterConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseTalonPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;

  public Shooter(final ShooterComponents components) {
    this.components = components;
    Shuffleboard.getTab("Shooter").addNumber("PID Error",
        () -> components.getMasterMotor().getClosedLoopError());
    Shuffleboard.getTab("Shooter").addNumber("Current velocity",
        () -> components.getMasterMotor().getClosedLoopError());
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
    components.getDoubleSolenoid().set(OPEN_SOLENOID_VALUE);
  }

  public void closeShooterPiston() {
    components.getDoubleSolenoid().set(CLOSE_SOLENOID_VALUE);
  }

  public double distanceToVelocity(double distance) {
    return -0.0003 * Math.pow(distance, 3) + 0.5381 * Math.pow(distance, 2) - 285.34 * distance + 82347;
  }
 // y = -0.0003x3 + 0.5381x2 - 285.34x + 82347
}
