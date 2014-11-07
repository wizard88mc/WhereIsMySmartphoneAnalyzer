package features;

import java.util.ArrayList;

/**
 * Holds data for a sensor with three axes of data
 * @author Matteo Ciman
 */
public class ThreeAxesData 
{
    protected DataSet x = null;
    protected DataSet y = null;
    protected DataSet z = null;
    
    public ThreeAxesData(String first, ArrayList<Float> xData, String second, 
            ArrayList<Float> yData, String third, ArrayList<Float> zData)
    {
        x = new DataSet(first, xData); y = new DataSet(second, yData);
        z = new DataSet(third, zData);
    }
}
