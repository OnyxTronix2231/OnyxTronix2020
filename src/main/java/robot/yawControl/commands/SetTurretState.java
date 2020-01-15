package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.yawControl.YawControl;
import robot.yawControl.YawControl.TurretState;

public class SetTurretState extends CommandBase {

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
