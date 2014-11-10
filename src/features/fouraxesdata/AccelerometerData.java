package features.fouraxesdata;

import features.fouraxesdata.FourAxesMotionSensorData;
import java.util.ArrayList;

/**
 * AccelerometerData holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class AccelerometerData extends FourAxesMotionSensorData
{
    public AccelerometerData(ArrayList<Float> x, ArrayList<Float> y, ArrayList<Float> z)
    {
        super("X", x, "Y", y, "Z", z, "|V|");
        firstAxis.calculateData(); secondAxis.calculateData(); 
        thirdAxis.calculateData(); fourthAxis.calculateData();
    }
}
