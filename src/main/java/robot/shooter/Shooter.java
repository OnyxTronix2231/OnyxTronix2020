package robot.shooter;

import static robot.RobotConstants.PRIMARY_PID;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_PID_SLOT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.CLOSE_SOLENOID_VALUE;
import static robot.shooter.ShooterConstants.ShooterComponentsA.OPEN_SOLENOID_VALUE;

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

  public void configVelocitySlot() {
    components.getMasterMotor().selectProfileSlot(VELOCITY_PID_SLOT, PRIMARY_PID);
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }

  public void openShooterSolenoid() {
    components.getDoubleSolenoid().set(OPEN_SOLENOID_VALUE);
  }

  public void closeShooterSolenoid() {
    components.getDoubleSolenoid().set(CLOSE_SOLENOID_VALUE);
  }
}
