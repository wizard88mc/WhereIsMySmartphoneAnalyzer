package features;

import java.util.ArrayList;

/**
 * Linear data holder
 * @author  Matteo Ciman
 * @since   2014-11-07
 * @version 0.1
 */
public class LinearData extends ThreePlusOneAxesMotionSensor 
{
    public LinearData(ArrayList<Float> x, ArrayList<Float> y, ArrayList<Float> z)
    {
        super(x, y, z);
    }
}
