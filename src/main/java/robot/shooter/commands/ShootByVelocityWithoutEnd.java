package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByVelocityWithoutEnd extends CommandBase {

  private final Shooter shooter;
  private final DoubleSupplier velocitySupplier;

  public ShootByVelocityWithoutEnd(final Shooter shooter, final DoubleSupplier velocitySupplier) {
    this.shooter = shooter;
    this.velocitySupplier = velocitySupplier;
    addRequirements(shooter);
  }

  @Override
  public void execute() {
    shooter.setVelocity(velocitySupplier.getAsDouble());
  }

}
