package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootBySpeed extends CommandBase {

  private final DoubleSupplier speedSupplier;
  private final Shooter shooter;

  public ShootBySpeed(final Shooter shooter, final DoubleSupplier speedSupplier) {
    this.shooter = shooter;
    this.speedSupplier = speedSupplier;
    addRequirements(shooter);
  }

  @Override
  public void execute() {
    shooter.shootBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    shooter.stopMotor();
  }
}
