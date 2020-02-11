package robot.shooter.commands;

import robot.shooter.Shooter;
import robot.vision.VisionCalculations;
import robot.vision.limelight.Limelight;

public class ShootByLimelight extends ShootByDistance {

  public ShootByLimelight(Shooter shooter) {
    super(shooter, () -> VisionCalculations.calculateDistance(Limelight.getInstance().getTarget()));
  }

  @Override
  public boolean isFinished() {
    return !Limelight.getInstance().targetFound() || super.isFinished();
  }
}
