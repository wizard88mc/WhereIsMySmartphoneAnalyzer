package features.threeaxesdata;

import java.util.ArrayList;

/**
 * This class holds the data for the Gravity sensor
 * @author Matteo Ciman
 * @since 2014-11-08
 * @version 0.1
 */
public class GravityData extends ThreeAxesData 
{
    public GravityData(ArrayList<Float> xData, ArrayList<Float> yData, 
            ArrayList<Float> zData)
    {
        super("X_GRAVITY", xData, "Y_GRAVITY", yData, "Z_GRAVITY", zData);
    }
}
