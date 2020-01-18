package robot.TransportToShooter;

public class TransportToShooter {

    private final BasicTransportToShooterComponents components;

    public TransportToShooter(final BasicTransportToShooterComponents moveTransportToShooterBySpeed) {
        this.components = moveTransportToShooterBySpeed;
    }

    public final void moveTransportToShooterBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public final void stopMotorFirstTransportToShooter() {
        components.getMasterMotor().set(0);
    }
}
