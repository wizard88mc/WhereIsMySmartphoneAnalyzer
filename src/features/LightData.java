/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import java.util.ArrayList;

/**
 * This class holds the data from the Light sensor
 * @author Matteo Ciman
 * @version 0.1
 * @since 2014-11-08
 */
public class LightData extends OneAxisData
{
    public LightData(ArrayList<Float> data, Float maxValue)
    {
        super("LIGHT", data, maxValue);
        
        correctValuesUsingMaxValue();
    }
}
