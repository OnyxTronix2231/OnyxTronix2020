package robot.leds;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.Timer;

import static robot.leds.RGBLEDsConstants.*;

public class RGBLEDs extends SubsystemBase {

  private final CANifier canifier;

  public RGBLEDs(final CANifier canifier){
    this.canifier = canifier;
    configCanifier();
  }

  public void changeColor(final char ch){
    switch (ch) {
      /**
       A: G
       B: R
       C: B
       */
      case 'W':
        canifier.setLEDOutput(WHITE_G, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(WHITE_R, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(WHITE_B, CANifier.LEDChannel.LEDChannelC);
      case 'R':
        canifier.setLEDOutput(RED_G, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(RED_R, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(RED_B, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'G':
        canifier.setLEDOutput(GREEN_G, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(GREEN_R, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(GREEN_B, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'B':
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(100, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'Y':
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(100, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(100, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'P':
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(90, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(90, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'O':
        canifier.setLEDOutput(50, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(100, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'N':
          canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelA);
          canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelB);
          canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelC);
    }
  }

  public void configCanifier(){
    canifier.configFactoryDefault();
  }
}
