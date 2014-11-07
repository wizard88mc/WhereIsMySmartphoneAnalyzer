package features;

import java.util.ArrayList;

/**
 * Rotation sensor data holder
 * 
 * @author Matteo Ciman
 * @since 2014-11-07
 * @version 0.1
 */
public class RotationData extends ThreeAxesData 
{
    public RotationData(ArrayList<Float> xData, ArrayList<Float> yData, 
            ArrayList<Float> zData)
    {
        super("RotationX", xData, "RotationY", yData, "RotationZ", zData);
    }
}
