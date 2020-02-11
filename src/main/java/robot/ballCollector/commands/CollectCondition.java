package robot.ballCollector.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.ballCollector.BallCollector;

public class CollectCondition extends ConditionalCommand {

  private final CollectAndCount collectAndCount;

  public CollectCondition(final CollectAndCount collectAndCount, final BallCollector ballCollector){
    super(collectAndCount,new InstantCommand(), ballCollector::canCollect);
    this.collectAndCount = collectAndCount;
  }
}
