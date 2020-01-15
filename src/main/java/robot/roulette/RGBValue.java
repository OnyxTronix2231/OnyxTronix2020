package robot.roulette;

import edu.wpi.first.wpilibj.util.Color;

public class RGBValue {
  public static final double RGB_EQUALS_THRESHOLD = 0.8;
  private final double red;
    private final double green;
    private final double blue;

    public RGBValue(final double red, final double green, final double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public RGBValue colorToRgb(Color color) {
        return new RGBValue(color.red, color.green, color.blue);
    }

    public double howCloseTo(final Color color) {
        return howCloseTo(colorToRgb(color));
    }

    public double howCloseTo(final RGBValue anotherColor) {
        return 1 - (squaredError(anotherColor.red, this.red) +
                squaredError(anotherColor.green, this.green) +
                squaredError(anotherColor.blue, this.blue));
    }

    public boolean equals(final RGBValue other) {
        return howCloseTo(other) >= RGB_EQUALS_THRESHOLD;
    }

    public boolean equals(final Color other) {
        return equals(colorToRgb(other));
    }

    private double squaredError(final double value, final double target) {
        return Math.pow(value - target, 2);
    }
}
