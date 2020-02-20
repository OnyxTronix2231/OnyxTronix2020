package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

public class NormalizedPigeonIMU extends PigeonIMU implements Sendable {

  public NormalizedPigeonIMU(int deviceNumber) {
    super(deviceNumber);
    SendableRegistry.addLW(this, "PigeonIMU [" + deviceNumber + "]");
  }

  public NormalizedPigeonIMU(TalonSRX talonSrx) {
    super(talonSrx);
    SendableRegistry.addLW(this, "PigeonIMU [" + talonSrx.getDeviceID() + "]");
  }

  public double getRawYaw() {
    double[] yawPitchRoll = new double[3];
    getYawPitchRoll(yawPitchRoll);
    return Math.IEEEremainder(yawPitchRoll[0], 360);
  }

  public double getNormalizedYaw() {
    final double rawYaw = getRawYaw();
    if (rawYaw > 180) {
      return rawYaw - 360;
    } else if (rawYaw < -180) {
      return rawYaw + 360;
    }
    return rawYaw;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Gyro");
    builder.addDoubleProperty("Value", this::getRawYaw, null);
  }
}
