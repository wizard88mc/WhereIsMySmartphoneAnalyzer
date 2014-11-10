package features.fouraxesdata;

import features.fouraxesdata.FourAxesMotionSensorData;
import java.util.ArrayList;

/**
 * Accelerometer without gravity data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class AccelerometerNoGravityData extends FourAxesMotionSensorData 
{
    public AccelerometerNoGravityData(ArrayList<Float> x, ArrayList<Float> y, 
            ArrayList<Float> z)
    {
        super("X_NO_G", x, "Y_NO_G", y, "Z_NO_G", z, "V_NO_G");
        firstAxis.calculateData(); secondAxis.calculateData();
        thirdAxis.calculateData(); fourthAxis.calculateData();
    }
}
