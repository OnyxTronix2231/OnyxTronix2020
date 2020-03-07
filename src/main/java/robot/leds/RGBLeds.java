package robot.leds;

import static robot.leds.RGBLedsConstants.BLUE_B;
import static robot.leds.RGBLedsConstants.BLUE_G;
import static robot.leds.RGBLedsConstants.BLUE_R;
import static robot.leds.RGBLedsConstants.GREEN_B;
import static robot.leds.RGBLedsConstants.GREEN_G;
import static robot.leds.RGBLedsConstants.GREEN_R;
import static robot.leds.RGBLedsConstants.NOTHING_B;
import static robot.leds.RGBLedsConstants.NOTHING_G;
import static robot.leds.RGBLedsConstants.NOTHING_R;
import static robot.leds.RGBLedsConstants.ORANGE_B;
import static robot.leds.RGBLedsConstants.ORANGE_G;
import static robot.leds.RGBLedsConstants.ORANGE_R;
import static robot.leds.RGBLedsConstants.PURPLE_B;
import static robot.leds.RGBLedsConstants.PURPLE_G;
import static robot.leds.RGBLedsConstants.PURPLE_R;
import static robot.leds.RGBLedsConstants.RED_B;
import static robot.leds.RGBLedsConstants.RED_G;
import static robot.leds.RGBLedsConstants.RED_R;
import static robot.leds.RGBLedsConstants.WHITE_B;
import static robot.leds.RGBLedsConstants.WHITE_G;
import static robot.leds.RGBLedsConstants.WHITE_R;
import static robot.leds.RGBLedsConstants.YELLOW_B;
import static robot.leds.RGBLedsConstants.YELLOW_G;
import static robot.leds.RGBLedsConstants.YELLOW_R;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RGBLeds extends SubsystemBase {

  private final CANifier canifier;

  public RGBLeds(final CANifier canifier) {
    this.canifier = canifier;
    configCanifier();
  }

  public void changeColor(final char ch) {
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
        canifier.setLEDOutput(BLUE_G, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(BLUE_R, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(BLUE_B, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'Y':
        canifier.setLEDOutput(YELLOW_G, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(YELLOW_R, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(YELLOW_B, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'P':
        canifier.setLEDOutput(PURPLE_G, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(PURPLE_R, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(PURPLE_B, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'O':
        canifier.setLEDOutput(ORANGE_G, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(ORANGE_R, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(ORANGE_B, CANifier.LEDChannel.LEDChannelC);
        break;
      case 'N':
        canifier.setLEDOutput(NOTHING_G, CANifier.LEDChannel.LEDChannelA);
        canifier.setLEDOutput(NOTHING_R, CANifier.LEDChannel.LEDChannelB);
        canifier.setLEDOutput(NOTHING_B, CANifier.LEDChannel.LEDChannelC);
    }
  }

  public void configCanifier() {
    canifier.configFactoryDefault();
  }
}
