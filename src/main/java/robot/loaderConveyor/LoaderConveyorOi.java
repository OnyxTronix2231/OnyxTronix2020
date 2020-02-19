package robot.loaderConveyor;

import static robot.loaderConveyor.LoaderConveyorConstants.SPEED;

import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;

public class LoaderConveyorOi {
  public LoaderConveyorOi(final LoaderConveyor loaderConveyor, final UniqueButtonCache buttonsJoystickButtonCache) {
    final Trigger moveLoaderConveyorBySpeed = buttonsJoystickButtonCache.createJoystickTrigger(Button.kA.value);
    moveLoaderConveyorBySpeed.whileActiveContinuous(new MoveLoaderConveyorBySpeed(loaderConveyor,
        () -> SPEED));
  }
}
