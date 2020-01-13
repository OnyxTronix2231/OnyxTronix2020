package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class TalonFXEncoder {
  private final int pidSlot;
  private final TalonFX talonFX;

  public TalonFXEncoder(final TalonFX talonFX, final int pidSlot) {
    this.talonFX = talonFX;
    this.pidSlot = pidSlot;
  }

  public int getCount() {
    return talonFX.getSelectedSensorPosition(pidSlot);
  }

  public double getRate() {
    return talonFX.getSelectedSensorVelocity(pidSlot);
  }

  public void reset() {
    talonFX.setSelectedSensorPosition(0, pidSlot, 100);
  }
}
