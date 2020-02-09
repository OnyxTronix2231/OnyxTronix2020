package robot.shooter.commands;

import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByVelocity extends ShootByVelocityWithoutEnd {

  private final Shooter shooter;
  public ShootByVelocity(final Shooter shooter,final DoubleSupplier velocitySupplier) {
    super(shooter, velocitySupplier);
    this.shooter = shooter;
  }

  @Override
    public void end(final boolean interrupted) {
    shooter.stopMotor();
  }
}
