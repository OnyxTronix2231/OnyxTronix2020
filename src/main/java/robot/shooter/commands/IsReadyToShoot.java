package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.shooter.Shooter;

public class IsReadyToShoot extends ConditionalCommand {

  public IsReadyToShoot(final ShootAndCount shootAndCount, final Shooter shooter){
    super(shootAndCount, new InstantCommand(), shooter::isReadyToShoot);
  }
}
