package features;

import java.util.ArrayList;

/**
 * This class is used to manage data for sensor with only one "axis" of data
 * @author  Matteo Ciman
 * @since   2014-11-08
 * @version 0.1
 */
public class OneAxisData 
{
    private float maxValue;
    protected DataSet firstAxis = null;
    
    public OneAxisData(String name, ArrayList<Float> data, Float maxValue)
    {
        firstAxis = new DataSet(name, data);
        this.maxValue = maxValue;
    }
    
    /**
     * Updates the DataSet using the max value of the sensor
     */
    protected void correctValuesUsingMaxValue()
    {
        firstAxis.touchDataUsingMaxValue(maxValue);
    }
}
