package robot.TransportToShooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.TransportToShooter.TransportToShooter;

import java.util.function.DoubleSupplier;

public class MoveBallTransportToShooterBySpeed extends CommandBase {
    private final robot.TransportToShooter.TransportToShooter TransportToShooter;
    private final DoubleSupplier speedSupplier;

    public MoveBallTransportToShooterBySpeed(final TransportToShooter transportToShooter, final DoubleSupplier speedSupplier) {
        this.TransportToShooter = transportToShooter;
        this.speedSupplier = speedSupplier;
    }

    @Override
    public void execute() {
        TransportToShooter.moveTransportToShooterBySpeed(speedSupplier.getAsDouble());
    }

    @Override
    public void end(final boolean interrupted) {
        TransportToShooter.stopMotor();
    }

}
