package robot.leds;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.Timer;

public class RGBLEDs extends SubsystemBase {

  private final CANifier canifier;

  public RGBLEDs(final CANifier canifier){
    this.canifier = canifier;
  }

  public void changeColor(final char ch){
    switch (ch) {
      /**
       A: G
       B: R
       C: B
       */
      case 'R':
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(100, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'G':
        canifier.setLEDOutput(100, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(0, CANifier.LEDChannel.LEDChannelC);
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
}
