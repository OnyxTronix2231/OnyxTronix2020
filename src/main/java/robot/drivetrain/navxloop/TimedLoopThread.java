package robot.drivetrain.navxloop;

import edu.wpi.first.hal.NotifierJNI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Watchdog;

import java.awt.*;

public class TimedLoopThread extends Thread {

  private final double period;
  private final Runnable runnable;
  private double expirationTime;
  private boolean run;
  private final String ePoch;


  public TimedLoopThread(final Runnable runnable, final double period, final String epoch) {
    this.period = period;
    this.runnable = runnable;
    this.ePoch = epoch;
  }

  public void disable() {
    run = false;
  }

  public void enable() {
    start();
  }

  @Override
  public void run() {
    run = true;

    while (run) {
      if(DriverStation.getInstance().isDisabled()) {
        break;
      }

      runnable.run();
    }
  }
}
