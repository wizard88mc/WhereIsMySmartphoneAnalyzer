package features.fouraxesdata;

import features.fouraxesdata.FourAxesMotionSensorData;
import java.util.ArrayList;

/**
 * Linear data holder
 * @author  Matteo Ciman
 * @since   2014-11-07
 * @version 0.1
 */
public class LinearData extends FourAxesMotionSensorData 
{
    public LinearData(ArrayList<Float> x, ArrayList<Float> y, ArrayList<Float> z)
    {
        super("XL", x, "YL", y, "ZL", z, "|V|L");
    }
}
