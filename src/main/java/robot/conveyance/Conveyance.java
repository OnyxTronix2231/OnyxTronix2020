package robot.conveyance;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyance extends SubsystemBase {

    private final BasicConveyanceComponents components;
    private final BasicSecondaryConveyance SecondaryComponents;

    public Conveyance(final BasicConveyanceComponents components, final BasicSecondaryConveyance secondaryComponents) {
        this.components = components;
        this.SecondaryComponents = secondaryComponents;
    }

    public final void moveConveyanceComponents(final double speed){
        components.getMasterMotor().set(ControlMode.PercentOutput,speed);
    }

    public final void stopMotorConveyanceComponents(){
        components.getMasterMotor().set(ControlMode.PercentOutput,0);
    }

    public final void moveSecondaryConveyance(final double speed){
        SecondaryComponents.getMasterMotor().set(ControlMode.PercentOutput,speed);
    }

    public final void stopMotorSecondaryConveyance(){
        SecondaryComponents.getMasterMotor().set(ControlMode.PercentOutput,0);
    }
}
