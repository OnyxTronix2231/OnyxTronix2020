package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

import java.util.function.BooleanSupplier;

public class WaitUntilWithCounter extends WaitUntilCommand {

  private double counter;

  public WaitUntilWithCounter(BooleanSupplier condition) {
    super(condition);
  }

  @Override
  public void initialize() {
    super.initialize();
    counter = 0;
  }

  @Override
  public void execute() {
    if (super.isFinished()) {
      counter++;
    } else {
      counter = 0;
    }
  }

  @Override
  public boolean isFinished() {
    return super.isFinished() && counter > 5;
  }
}
