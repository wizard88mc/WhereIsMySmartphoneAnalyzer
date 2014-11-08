package features.fouraxesdata;

import features.fouraxesdata.FourAxesMotionSensorData;
import java.util.ArrayList;

/**
 * Linear rotated data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class LinearRotatedData extends FourAxesMotionSensorData 
{
    public LinearRotatedData(ArrayList<Float> x, ArrayList<Float> y, 
            ArrayList<Float> z)
    {
        super("XL_R", x, "YL_R", y, "ZL_R", z, "|V|L_R");
    }
}
