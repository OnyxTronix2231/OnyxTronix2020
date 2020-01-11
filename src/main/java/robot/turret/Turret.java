package robot.turret;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

  private final TurretComponents components;

  public Turret(TurretComponents components) {
    this.components = components;
  }

  public void moveBySpeed(final double speed){
    components.getMasterMotor().set(speed);
  }



}
