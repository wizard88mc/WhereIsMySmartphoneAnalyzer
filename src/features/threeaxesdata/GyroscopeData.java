package features.threeaxesdata;

import java.util.ArrayList;

/**
 * This class holds data for the Gyroscope sensor
 * @author Matteo Ciman
 * @since 2014-11-08
 * @since 0.1
 */
public class GyroscopeData extends ThreeAxesData
{
    public GyroscopeData(ArrayList<Float> xData, ArrayList<Float> yData, 
            ArrayList<Float> zData)
    {
        super("X_GYROSCOPE", xData, "Y_GYROSCOPE", yData, "Z_GYROSCOPE", zData);
    }
}
