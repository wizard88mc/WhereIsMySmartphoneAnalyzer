package models;

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
    
    private List<Reading> readings = null;
    
    public Exercise(int trunkAccelerometer, int trunkLinear, String sex, 
            String age, String height, String shoes, 
            String hand, String action, String origin, String destination) 
    {
        
        this.trunkAccelerometer = trunkAccelerometer; this.trunkLinear = trunkLinear;
        this.sex = sex; this.age = age; this.height = height;
        this.shoes = shoes; this.hand = hand; this.action = action;
        this.origin = origin; this.destination = destination; 
    }
}
