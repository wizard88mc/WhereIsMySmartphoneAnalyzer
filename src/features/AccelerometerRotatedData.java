package features;

import java.util.ArrayList;

/**
 * Accelerometer rotated data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class AccelerometerRotatedData extends ThreePlusOneAxesMotionSensor 
{
    public AccelerometerRotatedData(ArrayList<Float> x, ArrayList<Float> y, 
            ArrayList<Float> z)
    {
        super(x, y, z);
    }
}
