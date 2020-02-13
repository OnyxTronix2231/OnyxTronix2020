package robot.shooter;

import static robot.shooter.ShooterConstants.MAX_FIRST_RANGE_CM;
import static robot.shooter.ShooterConstants.MIN_FIRST_RANGE_CM;
import static robot.shooter.ShooterConstants.MIN_THIRD_RANGE_CM;
import static robot.shooter.ShooterConstants.SPEED_FIRST;
import static robot.shooter.ShooterConstants.SPEED_MIDDLE;
import static robot.shooter.ShooterConstants.SPEED_THIRD;
import static robot.shooter.ShooterConstants.ShooterComponents.*;
import static robot.shooter.ShooterConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;
  private double velocity;

  public Shooter(final ShooterComponents components) {
    this.components = components;
    Shuffleboard.getTab("Shooter").add("velocity_p", VELOCITY_P).
        getEntry().addListener(
        p -> components.getMasterMotor().config_kP(0, p.value.getDouble()),
        EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").add("velocity_I", VELOCITY_I).
        getEntry().addListener(
        i -> components.getMasterMotor().config_kI(0, i.value.getDouble()),
        EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").add("velocity_D", VELOCITY_D).
        getEntry().addListener(
        d -> components.getMasterMotor().config_kD(0, d.value.getDouble()),
        EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").addNumber("velocity",
        () -> components.getMasterMotor().getSelectedSensorVelocity());

    Shuffleboard.getTab("Shooter").addNumber
        ("outputAMP", () -> components.getMasterMotor().getStatorCurrent());

    Shuffleboard.getTab("Shooter").addNumber("error", () -> components.getMasterMotor().getClosedLoopError());

  }

  @Override
  public void periodic() {
  }

  public void shootBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
    velocity = MAX_VELOCITY * speed;
  }

  public void stopMotor() {
    shootBySpeed(0);
    velocity = 0;
  }

  public double getSetPoint() {
    return velocity;
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
    this.velocity = velocity;
  }

  public boolean isOnTarget() {
    return components.getMasterMotor().getClosedLoopError() < TOLERANCE;
  }

  public int distanceToVelocity(final double distance){
    if (distance > MIN_THIRD_RANGE_CM) {
      return SPEED_THIRD;
    } else if (distance > MAX_FIRST_RANGE_CM && distance < MIN_THIRD_RANGE_CM) {
      return SPEED_MIDDLE;
    } else if (distance > MIN_FIRST_RANGE_CM){
      return SPEED_FIRST;
    } else {
      return 0;
    }
  }
}
