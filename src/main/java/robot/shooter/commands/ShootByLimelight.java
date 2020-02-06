package robot.shooter.commands;

import edu.wpi.first.wpilibj.DriverStation;
import robot.shooter.Shooter;
import robot.vision.VisionCalculations;
import robot.vision.VisionTarget;
import robot.vision.limelight.Limelight;
import java.util.function.DoubleSupplier;

public class ShootByLimelight extends ShootByDistance {

  public ShootByLimelight(Shooter shooter) {
    super(shooter, () -> VisionCalculations.calculateDistance(Limelight.getInstance().getTarget()));
  }

  @Override
  public boolean isFinished() {
    return !Limelight.getInstance().targetFound() || super.isFinished();
  }
}
