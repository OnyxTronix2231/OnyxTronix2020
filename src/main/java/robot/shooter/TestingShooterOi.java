package robot.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.shooter.commands.ShootBySpeed;
import onyxTronix.UniqueButtonCache;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootByVelocity;

import static robot.shooter.ShooterConstants.PERCENT_OUT_PUT;

public class TestingShooterOi {

  public TestingShooterOi(final UniqueAxisCache buttonJoystickAxisCache,
                          UniqueButtonCache driveJoystickButtonCache, final Shooter shooter) {
    final JoystickAxis shootBySpeedAxis = buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftY.value);
    shootBySpeedAxis.whileActiveContinuous(new ShootBySpeed(shooter, shootBySpeedAxis::getRawAxis));

    final Trigger openPiston = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    openPiston.whileActiveOnce(new OpenShooterPiston(shooter));

    final Trigger closePiston = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickRight.value);
    closePiston.whileActiveOnce(new CloseShooterPiston(shooter));

  }
}
