package robot.vision.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.vision.Vision;
import vision.limelight.enums.LimelightLedMode;

public class TurnLEDOff extends InstantCommand {

  private final Vision vision;

  public TurnLEDOff(Vision vision) {
    this.vision = vision;
    addRequirements(vision);
  }

  @Override
  public void initialize() {
    vision.setLEDMode(LimelightLedMode.forceOff);
  }
}
