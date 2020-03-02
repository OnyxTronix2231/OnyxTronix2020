package robot.loaderConveyor;

import static robot.loaderConveyor.LoaderConveyorConstants.PERCENTAGE_OUTPUT_MIN;

import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;

public class TestingLoaderConveyorOi {
  public TestingLoaderConveyorOi(final LoaderConveyor loaderConveyor, final UniqueButtonCache buttonsJoystickButtonCache) {
    final Trigger moveLoaderConveyorBySpeed = buttonsJoystickButtonCache.createJoystickTrigger(Button.kX.value);
    moveLoaderConveyorBySpeed.whileActiveContinuous(new MoveLoaderConveyorBySpeed(loaderConveyor,
        () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX));
  }
}
