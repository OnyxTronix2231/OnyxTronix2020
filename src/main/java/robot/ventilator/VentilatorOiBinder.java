package robot.ventilator;

import static robot.ventilator.VentilatorConstants.VentilatorComponentsA.PERCENTAGE_OUTPUT;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import robot.ventilator.commands.MoveVentilatorBySpeed;
import robot.ventilator.commands.WriteValuesToFile;

public class VentilatorOiBinder {
  public VentilatorOiBinder(final Ventilator ventilator, final JoystickButton moveVentilatorBySpeed) {
    moveVentilatorBySpeed.whenHeld(new MoveVentilatorBySpeed(ventilator, () -> PERCENTAGE_OUTPUT));
    moveVentilatorBySpeed.whenHeld(new WriteValuesToFile(ventilator));
  }
}
