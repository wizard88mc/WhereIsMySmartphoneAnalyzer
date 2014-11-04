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
}
