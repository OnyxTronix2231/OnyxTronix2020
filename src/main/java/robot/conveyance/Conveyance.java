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

    public void moveConveyance(final double speed){
        components.getMasterMotor().set(ControlMode.PercentOutput,speed);
    }

    public  void stopMotor(){
        components.getMasterMotor().set(ControlMode.PercentOutput,0);
    }


}
