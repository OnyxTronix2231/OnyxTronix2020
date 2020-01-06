package robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import robot.shooter.BasicShooterComponents;
import robot.shooter.Shooter;
import robot.shooter.ShooterOi;
import robot.shooter.commands.ShootBySpeed;

public class Robot extends TimedRobot {

    private Shooter shooter;
    private XboxController xboxController;
    @Override
    public void robotInit() {
        xboxController = new XboxController(1);
        shooter = new Shooter(new BasicShooterComponents());
        final ShooterOi shooterOi = new ShooterOi(xboxController, new ShootBySpeed(shooter, 0.5));
    }

    @Override
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void testInit() {
        LiveWindow.setEnabled(true);
    }

    @Override
    public void disabledInit() {
        LiveWindow.setEnabled(false);
    }

    @Override
    public void disabledPeriodic() {
        CommandScheduler.getInstance().run();
    }
}
