package features;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * This class is used to manage data for sensor with only one "axis" of data
 * @author  Matteo Ciman
 * @since   2014-11-08
 * @version 0.1
 */
public class OneAxisData 
{
    private final Float maxValue;
    protected DataSet firstAxis = null;
    protected ArrayList<Float> features = new ArrayList<Float>();
    protected ArrayList<String> featuresName = new ArrayList<String>();
    
    public OneAxisData(String name, ArrayList<Float> data, Float maxValue)
    {
        firstAxis = new DataSet(name, data);
        this.maxValue = maxValue;
    }
    
    /**
     * Calculates all the features for the current object. It calculates only
     * the basic features for the first axis
     */
    public void calculateFeatures()
    {
        addBasicFeatures(firstAxis);
    }
    
    /**
     * Updates the DataSet using the max value of the sensor
     */
    protected void correctValuesUsingMaxValue()
    {
        firstAxis.touchDataUsingMaxValue(maxValue);
    }
    
    protected void addBasicFeatures(DataSet... params)
    {
        for (DataSet axis: params)
        {
            Float average = axis.getAverage();
            if (average != null)
            {
                featuresName.add(axis.getName() + "_AVERAGE");
                features.add(average);
            }
            
            Float variance = axis.getVariance();
            if (variance != null)
            {
                featuresName.add(axis.getName() + "_VARIANCE");
                features.add(axis.getVariance());
            }
            
            Float std = axis.getStd();
            if (std != null)
            {
                featuresName.add(axis.getName() + "_STD");
                features.add(axis.getStd());
            }
            
            Float difference = axis.getDifferenceMinMax();
            if (difference != null)
            {
                featuresName.add(axis.getName() + "_DIFFERENCE_MIN_MAX");
                features.add(difference);
            }
            
            Float ratio = axis.getRatioMinMax();
            if (ratio != null)
            {
                featuresName.add(axis.getName() + "_RATIO_MIN_MAX");
                features.add(ratio);
            }
        }
    }
    
    /**
     * Returns all the features name in a ARFF file format
     * @return the features name in ARFF format
     */
    public String featuresNameToARFF()
    {
        String toReturn = "";
        for (String name: featuresName)
        {
            toReturn += "@ATTRIBUTE " + name + " NUMERIC " + 
                    System.getProperty("line.separator");
        }
        return toReturn;
    }
    
    /**
     * Returns a row for the ARFF file with all the features
     * @return 
     */
    public String featuresToString()
    {
        String result = "";
        int counterUsefulValues = 0;
        for (Float element: features)
        {
            if (element != null && !element.isNaN() && !element.isInfinite())
            {
                NumberFormat format = new DecimalFormat("0.####");
                result += format.format(element) + ",";
                counterUsefulValues++;
            }
            else
            {
                result += "?,";
            }
            
        }
        if (counterUsefulValues != 0)
        {
            return result;
        }
        else
        {
            return "";
        }
    }
    
    public ArrayList<Float> getFeatures()
    {
        return this.features;
    }
    
    public ArrayList<String> getFeaturesName()
    {
        return this.featuresName;
    }
}
