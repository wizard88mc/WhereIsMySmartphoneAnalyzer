package features.threeaxesdata;

import java.util.ArrayList;

/**
 * This class holds data form the Magnetic Field sensor
 * @author Matteo Ciman
 * @since 2014-11-08
 * @version 0.1
 */
public class MagneticFieldData extends ThreeAxesData
{
    public MagneticFieldData(ArrayList<Float> xData, ArrayList<Float> yData, 
            ArrayList<Float> zData)
    {
        super("X_MAGNETIC_FIELD", xData, "Y_MAGNETIC_FIELD", yData, 
                "Z_MAGNETIC_FIELD", zData);
        firstAxis.calculateData(); secondAxis.calculateData();
        thirdAxis.calculateData();
    }
}
