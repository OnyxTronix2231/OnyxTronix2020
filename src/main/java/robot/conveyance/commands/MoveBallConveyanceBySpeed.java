package robot.conveyance.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.conveyance.Conveyance;

import java.util.function.DoubleSupplier;

public class MoveBallConveyanceBySpeed extends CommandBase {
    private Conveyance conveyance;
    private DoubleSupplier speed;

    public MoveBallConveyanceBySpeed(Conveyance conveyance , DoubleSupplier speed) {
        this.conveyance = conveyance;
        this.speed = speed;
    }

    @Override
    public void initialize() {
        conveyance.moveConveyanceComponents(speed.getAsDouble());
        conveyance.moveSecondaryConveyance(speed.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        conveyance.stopMotorConveyanceComponents();
        conveyance.stopMotorSecondaryConveyance();
    }
}
