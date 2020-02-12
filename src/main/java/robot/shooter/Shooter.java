package robot.shooter;

import static robot.shooter.ShooterConstants.ShooterComponentsA.PRIMARY_PID;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_D;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_I;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_P;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_PID_SLOT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private final ShooterComponents components;

  public Shooter(final ShooterComponents components) {
    this.components = components;
    Shuffleboard.getTab("Shooter").add("velocity_p", VELOCITY_P).getEntry().addListener(
        p -> components.getMasterMotor().config_kP(VELOCITY_PID_SLOT, p.value.getDouble()),
        EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").add("velocity_I", VELOCITY_I).getEntry().addListener(
        i -> components.getMasterMotor().config_kI(VELOCITY_PID_SLOT, i.value.getDouble()),
        EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").add("velocity_D", VELOCITY_D).getEntry().addListener(
        d -> components.getMasterMotor().config_kD(VELOCITY_PID_SLOT, d.value.getDouble()),
        EntryListenerFlags.kUpdate);

    Shuffleboard.getTab("Shooter").addNumber("velocity",
        () -> components.getMasterMotor().getSelectedSensorVelocity());

    Shuffleboard.getTab("Shooter").addNumber
        ("outputAMP", () -> components.getMasterMotor().getStatorCurrent());

    Shuffleboard.getTab("Shooter").addNumber("error", () -> components.getMasterMotor().getClosedLoopError());
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
}
