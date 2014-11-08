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
        ArrayList<Float> v = new ArrayList<>();
        for (int i = 0; i < firstData.size(); i++)
        {
            if (firstData.get(i) != null && secondData.get(i) == null && 
                    thirdData.get(i) == null)
            {
                v.add((float) (Math.pow(firstData.get(i), 2) + 
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
}
