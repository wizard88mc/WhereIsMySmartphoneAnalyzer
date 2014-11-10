package features;

import java.util.ArrayList;

/**
 * This class holds data from the Ambient Temperature sensor
 * @author Matteo Ciman
 * @since 2014-11-08
 * @version 0.1
 */
public class AmbientTemperatureData extends OneAxisData
{
    public AmbientTemperatureData(ArrayList<Float> data, Float maxValue)
    {
        super("AMBIENT_TEMPERATURE", data, maxValue);
        
        correctValuesUsingMaxValue();
        firstAxis.calculateData();
    }
}
