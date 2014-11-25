package features.fouraxesdata;

import features.DataSet;
import java.util.ArrayList;

/**
 * This class holds data for a Motion Sensor with three axes plus a calculated one
 * @author Matteo Ciman
 */
public class FourAxesMotionSensorData extends ThreePlusOneAxes
{
    public FourAxesMotionSensorData(String first, ArrayList<Float> firstData, 
            String second, ArrayList<Float> secondData, String third, 
            ArrayList<Float> thirdData, String fourth)
    {
        super(first, firstData, second, secondData, third, thirdData);
        
        /**
         * Calculating data for the |V| axes
         */
        ArrayList<Float> v = new ArrayList<Float>();
        for (int i = 0; i < firstData.size(); i++)
        {
            if (firstData.get(i) != null && secondData.get(i) != null && 
                    thirdData.get(i) != null)
            {
                v.add((float) Math.sqrt(Math.pow(firstData.get(i), 2) + 
                        Math.pow(secondData.get(i), 2) + 
                    Math.pow(thirdData.get(i), 2)));
            }
            else
            {
                v.add(null);
            }
        }
        
        fourthAxis = new DataSet(fourth, v);
    }
    
    @Override
    public void calculateFeatures()
    {
        addBasicFeatures(firstAxis, secondAxis, thirdAxis, fourthAxis);
        
        features.addAll(calculateAverageRatios(firstAxis, secondAxis, thirdAxis, 
                fourthAxis));
        features.addAll(calculateStdRatios(firstAxis, secondAxis, thirdAxis, 
                fourthAxis));
        features.addAll(calculateVarianceRatios(firstAxis, secondAxis, thirdAxis, 
                fourthAxis));
        features.addAll(calculateMinRatios(firstAxis, secondAxis, thirdAxis, 
                fourthAxis));
        features.addAll(calculateMaxRatios(firstAxis, secondAxis, thirdAxis, 
                fourthAxis));
        features.addAll(calculateRatiosDifferenceMinMax(firstAxis, secondAxis, 
                thirdAxis, fourthAxis));
        calculateMagnitudeArea(firstAxis, secondAxis, thirdAxis, features);
        calculateSignalMagnitudeArea(firstAxis, secondAxis, thirdAxis, features);
        features.addAll(calculateCorrelation(firstAxis, secondAxis, thirdAxis, 
                fourthAxis));
    }
}
