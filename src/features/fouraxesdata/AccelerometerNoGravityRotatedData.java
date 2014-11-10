package features.fouraxesdata;

import features.fouraxesdata.FourAxesMotionSensorData;
import java.util.ArrayList;

/**
 * Accelerometer without gravity rotated data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class AccelerometerNoGravityRotatedData extends FourAxesMotionSensorData
{
    public AccelerometerNoGravityRotatedData(ArrayList<Float> x, 
            ArrayList<Float> y, ArrayList<Float> z)
    {
        super("X_NO_G_R", x, "Y_NO_G_R", y, "Z_NO_G_R", z, "|V|_NO_G_R");
        firstAxis.calculateData(); secondAxis.calculateData(); 
        thirdAxis.calculateData(); fourthAxis.calculateData();
    }
}
