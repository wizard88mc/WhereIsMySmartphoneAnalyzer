package models;

import java.util.ArrayList;
import java.util.List;

/**
 * class Exercise
 * 
 * This class stores all the information and settings related to an exercise (trunk)
 * 
 * @author Matteo Ciman
 */
public class Exercise 
{
    
    private final int trunkAccelerometer;
    private final int trunkLinear;
    private final String sex;
    private final String age;
    private final String height;
    private final String shoes;
    private final String hand;
    private final String action;
    private final String origin;
    private final String destination; 
    
    private ArrayList<Reading> readingsAccelerometer = null;
    private ArrayList<Reading> readingsLinear = null;
    
    public Exercise(int trunkAccelerometer, int trunkLinear, String sex, 
            String age, String height, String shoes, 
            String hand, String action, String origin, String destination) 
    {
        
        this.trunkAccelerometer = trunkAccelerometer; this.trunkLinear = trunkLinear;
        this.sex = sex; this.age = age; this.height = height;
        this.shoes = shoes; this.hand = hand; this.action = action;
        this.origin = origin; this.destination = destination; 
    }
    
    /**
     * Returns the trunk ID for the accelerometer data
     * @return the accelerometer trunk
     */
    public int getTrunkAccelerometer()
    {
        return this.trunkAccelerometer;
    }
    
    /**
     * Returns the trunk ID for the linear data
     * @return the linear trunk
     */
    public int getTrunkLinear()
    {
        return this.trunkLinear;
    }
    
    public String getDestination()
    {
        return this.destination;
    }
    
    public String getAction()
    {
        return this.action;
    }
    
    /**
     * Sets the reading both for the accelerometer and the linear
     * @param accelerometer ArrayList of accelerometer readings
     * @param linear ArrayList of linear readings
     */
    public void addReadings(ArrayList<Reading> accelerometer, 
            ArrayList<Reading> linear)
    {
        this.readingsAccelerometer = accelerometer;
        this.readingsLinear = linear;
    }
    
    /**
     * @return ArrayList accelerometer reading
     */
    public ArrayList<Reading> getReadingsAccelerometer()
    {
        return this.readingsAccelerometer;
    }
    
    /**
     * @return ArrayList of linear Reading 
     */
    public ArrayList<Reading> getReadingsLinear()
    {
        return this.readingsLinear;
    }
    
    /**
     * Called to rotate the basic and no gravity values for accelerometer readings,
     * and rotate only the basic values for the linear readings
     */
    public void rotateReadings()
    {
        if (readingsAccelerometer != null)
        {
            for (Reading reading: readingsAccelerometer)
            {
                reading.rotateBasicValues();
                reading.rotateNoGravityValues();
            }
        }
        
        if (readingsLinear != null)
        {
            for (Reading reading: readingsLinear)
            {
                reading.rotateBasicValues();
            }
        }
    }
    
    /**
     * Checks if the exercise has consistent proximity values, i.e. starting
     * from a point where the proximity value becomes 0, it checks if until the 
     * end the values of the proximity sensor remains 0
     * @return true if the proximity sensor data is coherent, false otherwise
     */
    public boolean hasConsistentProximityValue()
    {
        int startingPoint = -1;
        for (int i = 0; i < readingsAccelerometer.size() && startingPoint == -1; i++)
        {
            if (readingsAccelerometer.get(i).getProximityValue() == 0)
            {
                startingPoint = i;
            }
        }
        
        if (startingPoint == -1)
        {
            return false;
        }
        
        for (int i = startingPoint; i < readingsAccelerometer.size(); i++)
        {
            if (readingsAccelerometer.get(i).getProximityValue() != 0)
            {
                return false;
            }
        }
        
        return true;
    }
}
