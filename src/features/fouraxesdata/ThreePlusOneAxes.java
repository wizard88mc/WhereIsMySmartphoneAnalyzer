package features.fouraxesdata;

import features.DataSet;
import features.threeaxesdata.ThreeAxesData;
import java.util.ArrayList;

/**
 * This class is responsible to hold data for a sensor with three axis plus
 * a calculated one
 * @author  Matteo Ciman
 * @since   2014-11-07
 * @version 0.1
 */
public class ThreePlusOneAxes extends ThreeAxesData
{
    protected DataSet fourthAxis = null;
    
    public ThreePlusOneAxes(String firstName, ArrayList<Float> x, String secondName, 
            ArrayList<Float> y, String thirdName, ArrayList<Float> z)
    {
        super(firstName, x, secondName, y, thirdName, z);
    }
}
