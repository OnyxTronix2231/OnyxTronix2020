package robot.storageConveyor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StorageConveyor extends SubsystemBase {

    private final StorageConveyorComponents storageComponents;

    public StorageConveyor(final StorageConveyorComponents moveBySpeed) {
        this.storageComponents = moveBySpeed;
    }

    public final void moveBySpeed(final double speed) {
        storageComponents.getMotor().set(ControlMode.PercentOutput ,speed);
    }

    public final void stopMotor() {
        storageComponents.getMotor().set(ControlMode.PercentOutput,0);
    }
}
