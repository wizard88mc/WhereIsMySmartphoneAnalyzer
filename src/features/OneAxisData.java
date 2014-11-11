package features;

import com.sun.xml.internal.ws.util.StringUtils;
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
    protected ArrayList<Float> features = new ArrayList<>();
    protected ArrayList<String> featuresName = new ArrayList<>();
    
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
            featuresName.add(axis.getName() + "_AVERAGE");
            features.add(axis.getAverage());
            
            featuresName.add(axis.getName() + "_VARIANCE");
            features.add(axis.getVariance());
            
            featuresName.add(axis.getName() + "_STD");
            features.add(axis.getStd());
            
            featuresName.add(axis.getName() + "_DIFFERENCE_MIN_MAX");
            features.add(axis.getDifferenceMinMax());
            
            featuresName.add(axis.getName() + "_RATIO_MIN_MAX");
            features.add(axis.getRatioMinMax());
        }
    }
    
    /**
     * Returns all the features name in a ARFF file format
     * @return the features name in ARFF format
     */
    public String featuresNameToARFF()
    {
        String toReturn = "@RELATION WhereIsSmartphone" + 
                System.getProperty("line.separator");
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
        for (Float element: features)
        {
            if (element != null)
            {
                result += element.toString() + ",";
            }
            else
            {
                result += "?,";
            }
            
        }
        return result;
    }
}
