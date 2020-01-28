package robot.drivetrain.navxloop;

import static robot.drivetrain.DriveTrainConstants.NAVX_REFRESH_RATE;

import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainConstants;

public class NavXPIDLoop {

  private final DriveTrain driveTrain;
  private static final double period = (double)1 / NAVX_REFRESH_RATE - 10; // in seconds
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
    return !timedLoopThread.isAlive();
  }

  private void loop() {
    if(driveTrain.isGyroPIDatSetPoint()) {
      driveTrain.arcadeDrive(0,0);
      return;
    }
    driveTrain.arcadeDrive(0, -driveTrain.calculateGyroPIDProduct());
  }
}
