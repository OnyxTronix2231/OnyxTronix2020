package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.yawControl.YawControl;
import robot.yawControl.YawControl.TurretState;

public class SetTurretState extends InstantCommand {

  private final YawControl yawControl;
  private final TurretState turretState;

  public SetTurretState(YawControl yawControl, TurretState turretState) {
    this.yawControl = yawControl;
    this.turretState = turretState;
  }

  @Override
  public void initialize() {
    yawControl.setTurretState(turretState);
  }
}
