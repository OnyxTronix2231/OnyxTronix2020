package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import vision.limelight.Limelight;

public class VisionCondition extends ConditionalCommand {

  public VisionCondition(Command onTrue, Command onFalse) {
    super(onTrue, onFalse, () -> Limelight.getInstance().targetFound());
  }
}
