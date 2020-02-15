package robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;

public class ShootCondition extends ConditionalCommand {

  public ShootCondition(final ShootAndCount shootAndCount, final BallCollector ballCollector){
    super(shootAndCount, new InstantCommand(), ballCollector::canShoot);
  }
}
