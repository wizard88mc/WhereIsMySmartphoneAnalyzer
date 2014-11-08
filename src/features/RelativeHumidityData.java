package features;

import java.util.ArrayList;

/**
 * This class holds the data from the Relative Humidity sensor
 * @author Matteo Ciman
 * @since 2014-11-08
 * @version 0.1
 */
public class RelativeHumidityData extends OneAxisData 
{   
    public RelativeHumidityData(ArrayList<Float> data, Float maxValue)
    {
        super("RELATIVE_HUMIDITY", data, maxValue);
        
        correctValuesUsingMaxValue();
    }
}
