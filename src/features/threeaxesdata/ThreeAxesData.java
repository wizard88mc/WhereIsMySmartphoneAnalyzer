package features.threeaxesdata;

import features.DataSet;
import features.OneAxisData;
import java.util.ArrayList;

/**
 * Holds data for a sensor with three axes of data
 * @author Matteo Ciman
 * @version 0.1
 * @since 2014-11-08
 */
public class ThreeAxesData extends OneAxisData
{
    protected DataSet secondAxis = null;
    protected DataSet thirdAxis = null;
    
    public ThreeAxesData(String first, ArrayList<Float> xData, String second, 
            ArrayList<Float> yData, String third, ArrayList<Float> zData)
    {
        super(first, xData, null);
        secondAxis = new DataSet(second, yData);
        thirdAxis = new DataSet(third, zData);
    }
}
