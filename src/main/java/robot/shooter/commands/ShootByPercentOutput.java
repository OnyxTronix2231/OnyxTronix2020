package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByPercentOutput extends CommandBase {

  private final DoubleSupplier speedSupplier;
  private final Shooter shooter;

  public ShootByPercentOutput(final Shooter shooter, final DoubleSupplier speed) {
    this.shooter = shooter;
    this.speedSupplier = speed;
    addRequirements(shooter);
  }

  @Override
  public final void execute() {
    shooter.shootBySpeed(speedSupplier);
  }

  @Override
  public final boolean isFinished() {
    return false;
  }

  @Override
  public final void end(boolean interrupted) {
    shooter.stopMotor();
  }
}
