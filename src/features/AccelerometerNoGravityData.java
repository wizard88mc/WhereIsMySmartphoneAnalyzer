package features;

import java.util.ArrayList;

/**
 * Accelerometer without gravity data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class AccelerometerNoGravityData extends ThreePlusOneAxesMotionSensor 
{
    public AccelerometerNoGravityData(ArrayList<Float> x, ArrayList<Float> y, 
            ArrayList<Float> z)
    {
        super(x, y, z);
    }
}
