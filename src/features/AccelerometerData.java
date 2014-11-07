package features;

import java.util.ArrayList;

/**
 * AccelerometerData holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class AccelerometerData extends ThreePlusOneAxesMotionSensor
{
    public AccelerometerData(ArrayList<Float> x, ArrayList<Float> y, ArrayList<Float> z)
    {
        super(x, y, z);
    }
}
