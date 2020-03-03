package robot.leds;

public class HsvToRgb {

  static float RGB[] = new float[3];

  public static float[] convert(double hDegrees, double S, double V) {
    double R, G, B;
    double H = hDegrees;

    if (H < 0) {
      H += 360;
    }
    if (H >= 360) {
      H -= 360;
    }

    if (V <= 0) {
      R = G = B = 0;
    } else if (S <= 0) {
      R = G = B = V;
    } else {
      double hf = H / 60.0;
      int i = (int) Math.floor(hf);
      double f = hf - i;
      double pv = V * (1 - S);
      double qv = V * (1 - S * f);
      double tv = V * (1 - S * (1 - f));
      switch (i) {
        /* Red is dominant color */
        case 0 :
        case 6 :
          R = V;
          G = tv;
          B = pv;
          break;
        /* Green is dominant color */
        case 1 :
          R = qv;
          G = V;
          B = pv;
          break;
        case 2 :
          R = pv;
          G = V;
          B = tv;
          break;
        /* Blue is the dominant color */
        case 3 :
          R = pv;
          G = qv;
          B = V;
          break;
        case 4 :
          R = tv;
          G = pv;
          B = V;
          break;
        /* Red is the dominant color */
        case 5 :
        case -1 :
          R = V;
          G = pv;
          B = qv;
          break;
        default :
          R = G = B = V;
          break;
      }
    }
    RGB[0] = (float) R;
    RGB[1] = (float) G;
    RGB[2] = (float) B;

    return RGB;
  }
}
