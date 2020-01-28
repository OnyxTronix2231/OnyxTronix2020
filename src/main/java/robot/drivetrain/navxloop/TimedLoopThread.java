package robot.drivetrain.navxloop;

import edu.wpi.first.hal.NotifierJNI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Watchdog;

public class TimedLoopThread extends Thread {

  private final int notifier; // C Pointer to Notifier
  private final double period;
  private final Runnable runnable;
  private double expirationTime;
  private final Watchdog watchdog;
  private boolean run;
  private final String ePoch;


  public TimedLoopThread(final Runnable runnable, final double period, final String epoch) {
    this.period = period;
    this.notifier = NotifierJNI.initializeNotifier();
    this.runnable = runnable;
    this.ePoch = epoch;
    this.watchdog = new Watchdog(period, () -> {
      System.out.println("Timed Out disabling Thread");
    });
  }

  @Override
  protected void finalize() { // cleans C Object
    NotifierJNI.stopNotifier(notifier);
    NotifierJNI.cleanNotifier(notifier);
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
    expirationTime = RobotController.getFPGATime() * 1e-6 + period;
    NotifierJNI.updateNotifierAlarm(notifier, (long) (expirationTime * 1e6));

    while (run) {
      if(NotifierJNI.waitForNotifierAlarm(notifier) == 0 || DriverStation.getInstance().isDisabled()) {
        break;
      }

      watchdog.reset();
      expirationTime += period;
      NotifierJNI.updateNotifierAlarm(notifier, (long) (expirationTime * 1e6));
      runnable.run();
      watchdog.addEpoch(ePoch);
    }
    watchdog.close();
  }
}
