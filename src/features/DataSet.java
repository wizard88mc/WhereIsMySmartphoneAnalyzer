package features;

import java.util.ArrayList;

/**
 * Given a set of Float data, it calculates basic values associated with this 
 * data set like min, max, average, variance, std, difference between min and
 * max value, ratio between min and max value
 * @author  Matteo Ciman
 * @since   2014-11-07
 * @version 0.1
 */
public class DataSet 
{
    private String name;
    private ArrayList<Float> dataSet;
    private int usefulData = 0;
    private Float min = Float.MAX_VALUE;
    private Float max = Float.MIN_VALUE;
    private Float average = null;
    private Float variance = null;
    private Float std = null;
    private Float differenceMinMax = null;
    private Float ratioMinMax = null;
    
    public DataSet(String name, ArrayList<Float> dataSet)
    {
        this.name = name; this.dataSet = dataSet;
    }
    
    /**
     * Edits all the readings using the max possible value of the sensor
     * @param maxValue the max value of the sensor
     */
    public void touchDataUsingMaxValue(Float maxValue)
    {
        if (maxValue != null)
        {
            for (Float value: dataSet)
            {
                if (value != null)
                {
                    value = value / maxValue;
                }
            }
        }
    }
    
    public void calculateData()
    {
        calculateMinMaxAndAverage();
        
        if (usefulData != 0)
        {
            calculateVarianceAndSTD();
        }
    }
    
    private void calculateMinMaxAndAverage()
    {
        float mean = 0;
        for (Float data: dataSet)
        {
            if (data != null)
            {
                if (data < min)
                {
                    min = data;
                }
                if (data > max)
                {
                    max = data;
                }
                usefulData++;
                mean += data;
            }
        }
        if (usefulData == 0)
        {
            min = null; max = null;
        }
        else
        {
            average = mean / (float)usefulData;
            differenceMinMax = Math.abs(min - max); 
            ratioMinMax = min / max;
        }
    }
    
    /**
     * If there is useful data inside the data set, it calculates the variance 
     * and the standard deviation
     */
    private void calculateVarianceAndSTD()
    {
        variance = 0f;
        for (Float data: dataSet)
        {
            if (data != null)
            {
                variance += (float) Math.pow(data - average, 2);
            }
        }
        variance /= (float)usefulData;
        
        std = (float) Math.sqrt(variance);
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Float getMin()
    {
        return this.min;
    }
    
    public Float getMax()
    {
        return this.max;
    }
    
    public Float getAverage()
    {
        return this.average;
    }
    
    public Float getVariance()
    {
        return this.variance;
    }
    
    public Float getStd()
    {
        return this.std;
    }
    
    public Float getDifferenceMinMax()
    {
        return this.differenceMinMax;
    }
    
    public Float getRatioMinMax()
    {
        return this.ratioMinMax;
    }
    
    public ArrayList<Float> getDataSet()
    {
            return this.dataSet;
    }
    
    public int getNumberData()
    {
        return this.dataSet.size();
    }
}
