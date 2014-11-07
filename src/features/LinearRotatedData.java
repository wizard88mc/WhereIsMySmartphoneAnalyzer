package features;

import java.util.ArrayList;

/**
 * Linear rotated data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class LinearRotatedData extends ThreePlusOneAxesMotionSensor 
{
    public LinearRotatedData(ArrayList<Float> x, ArrayList<Float> y, 
            ArrayList<Float> z)
    {
        super(x, y, z);
    }
}
