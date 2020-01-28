package robot.drivetrain.navxloop;

import static robot.drivetrain.DriveTrainConstants.NAVX_REFRESH_RATE;

import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainConstants;

public class NavXPIDLoop {

  private final DriveTrain driveTrain;
  private static final double period = 0.01; // in seconds
  private final TimedLoopThread timedLoopThread;

  public NavXPIDLoop(final DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    this.timedLoopThread = new TimedLoopThread(this::loop, period, "NavXPIDLoop");
  }

  public void start() {
    timedLoopThread.enable();
  }

  public void stop() {
    timedLoopThread.disable();
  }

  public boolean isFinished() {
    return false;
  }

  private void loop() {
    driveTrain.arcadeDrive(0, -driveTrain.calculateGyroPIDProduct());
  }
}
