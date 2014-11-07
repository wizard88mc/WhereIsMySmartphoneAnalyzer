/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import java.util.ArrayList;

/**
 * This class is responsible to hold data about the three motion axes (x,y,z)
 * plus the calculated |v| axis
 * @author  Matteo Ciman
 * @since   2014-11-07
 * @version 0.1
 */
public class ThreePlusOneAxesMotionSensor 
{
    protected DataSet xAxis = null;
    protected DataSet yAxis = null;
    protected DataSet zAxis = null;
    protected DataSet vAxis = null;
    
    public ThreePlusOneAxesMotionSensor(ArrayList<Float> x, ArrayList<Float> y, 
            ArrayList<Float> z)
    {
        ArrayList<Float> v = new ArrayList<>();
        for (int i = 0; i < x.size(); i++)
        {
            if (x.get(i) != null && y.get(i) == null && z.get(i) == null)
            {
                v.add((float) (Math.pow(x.get(i), 2) + Math.pow(y.get(i), 2) + 
                    Math.pow(z.get(i), 2)));
            }
            else
            {
                v.add(null);
            }
        }
        
        xAxis = new DataSet("X", x); yAxis = new DataSet("Y", y);
        zAxis = new DataSet("Z", z); vAxis = new DataSet("|V|", v);
    }
}
