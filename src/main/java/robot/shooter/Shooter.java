package robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

public class Shooter extends SubsystemBase {

    private ShooterComponents components;
    public static final double SPEED = 0.5;

    public Shooter (ShooterComponents components){
        this.components = components;
    }

    public void moveBySpeed(final DoubleSupplier speed){
        components.getMasterMotor().set(speed.getAsDouble());
    }

    public int getEncoder() {
        return components.getMasterMotor().getSelectedSensorPosition(0);
    }

    public void stopMotor(){
        components.getMasterMotor().set(0);
    }
}
