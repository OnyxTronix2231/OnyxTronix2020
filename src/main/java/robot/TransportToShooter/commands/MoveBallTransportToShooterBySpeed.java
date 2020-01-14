package robot.TransportToShooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.TransportToShooter.TransportToShooter;

import java.util.function.DoubleSupplier;

public class MoveBallTransportToShooterBySpeed extends CommandBase {
    private final robot.TransportToShooter.TransportToShooter TransportToShooter;
    private final DoubleSupplier speedSupplier;

    public MoveBallTransportToShooterBySpeed(TransportToShooter transportToShooter, DoubleSupplier speed) {
        this.TransportToShooter = transportToShooter;
        this.speedSupplier = speed;
    }

    @Override
    public void initialize() {
        TransportToShooter.moveTransportToShooterBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        TransportToShooter.stopMotorFirstTransportToShooter();
    }

}
