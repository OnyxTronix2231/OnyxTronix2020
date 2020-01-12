package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class TalonFXEncoder {
  private final TalonFX m_talonFX;
  private final int m_pidSlot;

  public TalonFXEncoder(final TalonFX talonFX, final int pidSlot) {
    m_talonFX = talonFX;
    m_pidSlot = pidSlot;
  }

  public int getCount() {
    return m_talonFX.getSelectedSensorPosition(m_pidSlot);
  }

  public double getRate() {
    return m_talonFX.getSelectedSensorVelocity(m_pidSlot);
  }

  public void reset() {
    m_talonFX.setSelectedSensorPosition(0, m_pidSlot, 100);
  }

}
