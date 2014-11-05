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
        for (Reading reading: readingsAccelerometer)
        {
            reading.rotateBasicValues();
            reading.rotateNoGravityValues();
        }
        
        for (Reading reading: readingsLinear)
        {
            reading.rotateBasicValues();
        }
    }
}
