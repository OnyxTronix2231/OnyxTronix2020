package robot.conveyance;

public class LoaderConveyor {

  private final BasicSecondaryConveyance components;

  public LoaderConveyor(final BasicSecondaryConveyance moveFirstConveyanceBySpeed, final BasicSecondaryConveyance secondaryComponents) {
    this.components = moveFirstConveyanceBySpeed;
  }

  public final void moveConveyanceBySpeed(final double speed){
    components.getMasterMotor().set(speed);
  }

  public final void stopMotorFirstConveyance(){
    components.getMasterMotor().set(0);
  }
}



