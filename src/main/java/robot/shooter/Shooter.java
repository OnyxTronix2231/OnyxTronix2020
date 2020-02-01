package robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;

  public Shooter(final ShooterComponents components) {
    this.components = components;
    Shuffleboard.getTab("Shooter").add("velocity_p", ShooterConstants.VELOCITY_P).
            getEntry().addListener(
                    p->components.getMasterMotor().config_kP(0, p.value.getDouble()),
                    EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").add("velocity_I", ShooterConstants.VELOCITY_I).
            getEntry().addListener(
            i->components.getMasterMotor().config_kI(0, i.value.getDouble()),
            EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").add("velocity_D", ShooterConstants.VELOCITY_D).
            getEntry().addListener(
            d->components.getMasterMotor().config_kD(0, d.value.getDouble()),
            EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").addNumber("velocity",
            () -> components.getMasterMotor().getSelectedSensorVelocity());

    Shuffleboard.getTab("Shooter").addNumber
            ("outputAMP" ,() -> components.getMasterMotor().getStatorCurrent());

  }

  @Override
  public void periodic() {
  }

  public void shootBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotor() {
    shootBySpeed(0);
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }

  }
