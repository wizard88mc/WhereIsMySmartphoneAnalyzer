package features;

import java.util.ArrayList;

/**
 * This class holds the data from the Pressure sensor
 * @author Matteo Ciman
 * @since 2014-11-08
 * @version 0.1
 */
public class PressureData extends OneAxisData 
{
    public PressureData(ArrayList<Float> data, Float maxValue)
    {
        super("PRESSURE", data, maxValue);
        
        correctValuesUsingMaxValue();
        firstAxis.calculateData();
    }
}
