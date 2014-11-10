package features.fouraxesdata;

import features.fouraxesdata.FourAxesMotionSensorData;
import java.util.ArrayList;

/**
 * Accelerometer rotated data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class AccelerometerRotatedData extends FourAxesMotionSensorData 
{
    public AccelerometerRotatedData(ArrayList<Float> x, ArrayList<Float> y, 
            ArrayList<Float> z)
    {
        super("G_R", x, "Y_R", y, "Z_R", z, "|V|_R");
        firstAxis.calculateData(); secondAxis.calculateData();
        thirdAxis.calculateData(); fourthAxis.calculateData();
    }
}
