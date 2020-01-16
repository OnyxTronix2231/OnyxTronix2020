package robot.LoaderConveyor;

public class LoaderConveyor {

    private final BasicLoaderConveyorComponents components;

    public LoaderConveyor(final BasicLoaderConveyorComponents moveBySpeed) {
        this.components = moveBySpeed;
    }

    public final void moveLoaderConveyorBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public final void stopMotor() {
        components.getMasterMotor().set(0);
    }
}
