package robot.ventilator.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ventilator.Ventilator;

import java.util.function.DoubleSupplier;

public class MoveVentilatorBySpeed extends CommandBase {

  private final Ventilator ventilator;
  private final DoubleSupplier speedSupplier;

  public MoveVentilatorBySpeed(Ventilator ventilator, DoubleSupplier speedSupplier) {
    this.ventilator = ventilator;
    this.speedSupplier = speedSupplier;
  }

  @Override
  public void execute() {
    ventilator.moveVentilatorBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    ventilator.stopMotor();
  }
}
