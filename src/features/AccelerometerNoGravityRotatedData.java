package features;

import java.util.ArrayList;

/**
 * Accelerometer without gravity rotated data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class AccelerometerNoGravityRotatedData extends ThreePlusOneAxesMotionSensor
{
    public AccelerometerNoGravityRotatedData(ArrayList<Float> x, 
            ArrayList<Float> y, ArrayList<Float> z)
    {
        super(x, y, z);
    }
}
