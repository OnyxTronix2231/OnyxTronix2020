package robot.conveyance;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyance extends SubsystemBase {

    private final BasicConveyanceComponents components;

    public Conveyance(final BasicConveyanceComponents components) {
        this.components = components;
        CommandScheduler.getInstance().registerSubsystem(this);
    }

    public final void moveConveyanceSystemOne(final double speed){
        components.getFirstMasterMotor().set(ControlMode.PercentOutput,speed);
    }

    public final void stopMotorSystemOne(){
        components.getFirstMasterMotor().set(ControlMode.PercentOutput,0);
    }

    public final void moveConveyanceSystemTwo(final double speed){
        components.getSecondMasterMotor().set(ControlMode.PercentOutput,speed);
    }

    public final void stopMotorSystemTwo(){
        components.getSecondMasterMotor().set(ControlMode.PercentOutput,0);
    }
}
