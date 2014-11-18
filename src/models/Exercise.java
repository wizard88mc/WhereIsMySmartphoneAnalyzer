package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class Exercise
 * 
 * This class stores all the information and settings related to an exercise (trunk)
 * 
 * @author Matteo Ciman
 */
public class Exercise 
{
    
    private static Map<String, String> englishToItalian = null;
    
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
    
    static {
        englishToItalian = new HashMap<>();
        englishToItalian.put("LEFT", "SINISTRA");
        englishToItalian.put("RIGHT", "DESTRA");
        englishToItalian.put("STANDING", "FERMO");
        englishToItalian.put("SITTING", "SEDUTO");
        englishToItalian.put("WALKING", "CAMMINANDO");
        englishToItalian.put("STAIRS_UP", "SCALE_SU");
        englishToItalian.put("STAIRS_DOWN", "SCALE_GIU");
        englishToItalian.put("CALL", "CHIAMATA");
        englishToItalian.put("HAND", "MANO");
        englishToItalian.put("NORMAL", "NORMALI");
        englishToItalian.put("WITH_HEEL", "CON_TACCO");
        englishToItalian.put("RIGHT_FRONT_POCKET", "TASCA_DESTRA_DAVANTI_PANTALONI");
        englishToItalian.put("RIGHT_BACK_POCKET", "TASCA_DESTRA_DIETRO_PANTALONI");
        englishToItalian.put("LEFT_FRONT_POCKET", "TASCA_SINISTRA_DAVANTI_PANTALONI");
        englishToItalian.put("LEFT_BACK_POCKET", "TASCA_SINISTRA_DIETRO_PANTALONI");
        englishToItalian.put("BOTTOM_JACKET", "TASCA_GIACCA_BASSA");
        englishToItalian.put("TOP_JACKET", "TASCA_GIACCA_ALTA");
        englishToItalian.put("BAG", "BORSA");
        englishToItalian.put("FANNY_PACK", "MARSUPIO");
        englishToItalian.put("BACKPACK", "ZAINO");
    }
    
    public Exercise(int trunkAccelerometer, int trunkLinear, String sex, 
            String age, String height, String shoes, 
            String hand, String action, String origin, String destination) 
    {
        
        this.trunkAccelerometer = trunkAccelerometer; this.trunkLinear = trunkLinear;
        this.sex = sex; this.age = age; this.height = height;
        if (englishToItalian.containsKey(action))
        {
            // ENGLISH_SETTINGS
            this.shoes = englishToItalian.get(shoes);
            this.hand = englishToItalian.get(hand);
            this.action = englishToItalian.get(action);
            this.origin = englishToItalian.get(origin);
            this.destination = englishToItalian.get(destination);
        }
        else
        {
            this.shoes = shoes; this.hand = hand; this.action = action;
            this.origin = origin; this.destination = destination; 
        }
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
        
        correctTimestampValues();
        correctProximityValues();
    }
    
    /**
     * Used to correct the bug of the timestamp value. Applied to all the exercises
     * to uniform sensor readings
     */
    private void correctTimestampValues()
    {
        long duration = 8000000000L;
        
        try
        {
            if (readingsAccelerometer != null)
            {
                int numberSamples = readingsAccelerometer.size();
                long delta = duration / numberSamples;
                for (int i = 0; i < readingsAccelerometer.size(); i++)
                {
                    readingsAccelerometer.get(i).setTime(
                            readingsAccelerometer.get(0).getTimestamp() 
                                    + i * delta);
                }
            }

            if (readingsLinear != null)
            {
                int numberSamples = readingsLinear.size();
                long delta = duration / numberSamples;
                for (int i = 0; i < readingsLinear.size(); i++)
                {
                    readingsLinear.get(i).setTime(
                            readingsLinear.get(0).getTimestamp() + 
                                    i * delta);
                }
            }
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        
    }
    
    private void correctProximityValues()
    {
        float minValueProximity = Float.MAX_VALUE, maxValueProximity = Float.MIN_VALUE;
        
        for (Reading reading: readingsAccelerometer)
        {
            if (reading.getProximityValue() < minValueProximity)
            {
                minValueProximity = reading.getProximityValue();
            }
            if (reading.getProximityValue() > maxValueProximity)
            {
                maxValueProximity = reading.getProximityValue();
            }
        }
        
        if (minValueProximity != maxValueProximity)
        {
            for (Reading reading: readingsAccelerometer)
            {
                if (reading.getProximityValue() == maxValueProximity)
                {
                    reading.setProximityValue(1);
                }
                else 
                {
                    reading.setProximityValue(0);
                }
            }
        }
        
        minValueProximity = Float.MAX_VALUE; maxValueProximity = Float.MIN_VALUE;
        if (readingsLinear != null)
        {
            for (Reading reading: readingsLinear)
            {
                if (reading.getProximityValue() < minValueProximity)
                {
                    minValueProximity = reading.getProximityValue();
                }
                if (reading.getProximityValue() > maxValueProximity)
                {
                    maxValueProximity = reading.getProximityValue();
                }
            }

            if (minValueProximity != maxValueProximity)
            {
                for (Reading reading: readingsLinear)
                {
                    if (reading.getProximityValue() == maxValueProximity)
                    {
                        reading.setProximityValue(1);
                    }
                    else 
                    {
                        reading.setProximityValue(0);
                    }
                }
            }
        }
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
        if (readingsAccelerometer.get(readingsAccelerometer.size() / 2).getProximityValue() != 1)
        {
            startingPoint = readingsAccelerometer.size() / 2;
            for (int i = startingPoint - 1; i >= 0 && startingPoint != readingsAccelerometer.size() / 2; i--)
            {
                if (readingsAccelerometer.get(i).getProximityValue() == 1)
                {
                    startingPoint = i;
                }
            }
            
            long beforeMovement = readingsAccelerometer.get(startingPoint).getTimestamp() 
                    - readingsAccelerometer.get(0).getTimestamp();
            
            if (!(beforeMovement >= 2000000000L))
            {
                return false;
            }
        }
        
        for (int i = readingsAccelerometer.size() / 2; i < readingsAccelerometer.size() 
                && startingPoint == -1; i++)
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
                readingsAccelerometer.get(i).setProximityValue(0);
            }
        }
        for (int i = startingPoint - 1; i >=0; i-- )
        {
            if (readingsAccelerometer.get(i).getProximityValue() != 1)
            {
                readingsAccelerometer.get(i).setProximityValue(1);
            }
        }
        
        if (readingsLinear != null)
        {
            correctLinearProximityData();
        }
        return true;
    }
    
    private void correctLinearProximityData()
    {
        int startingPoint = -1;
     
        if (readingsLinear.get(readingsLinear.size() / 2).getProximityValue() != 1)
        {
            startingPoint = readingsLinear.size() / 2;
            for (int i = startingPoint - 1; i >= 0 && startingPoint != readingsLinear.size() / 2; i--)
            {
                if (readingsLinear.get(i).getProximityValue() == 1)
                {
                    startingPoint = i;
                }
            }
        }
        
        for (int i = readingsLinear.size() / 2; i < readingsLinear.size() 
                && startingPoint == -1; i++)
        {
            if (readingsLinear.get(i).getProximityValue() == 0)
            {
                startingPoint = i;
            }
        }

        for (int i = startingPoint; i < readingsLinear.size(); i++)
        {
            if (readingsLinear.get(i).getProximityValue() != 0)
            {
                readingsLinear.get(i).setProximityValue(0);
            }
        }
        for (int i = startingPoint - 1; i >=0; i-- )
        {
            if (readingsLinear.get(i).getProximityValue() != 1)
            {
                readingsLinear.get(i).setProximityValue(1);
            }
        }
    }
    
    public void removeGravityFromAccelerometerData(long bufferDuration)
    {
        List<Reading> buffer = new ArrayList<>();
        boolean bufferFull = false;
        
        for (Reading reading: readingsAccelerometer)
        {
            if (buffer.size() > 0 && 
                    (reading.getTimestamp() - buffer.get(0).getTimestamp()) > bufferDuration)
            {
                bufferFull = true;
            }
            else
            {
                bufferFull = false;
                buffer.add(reading);
            }
            
            if (bufferFull)
            {
                float meanValueX = 0, meanValueY = 0, meanValueZ = 0;
                for (Reading readingB: buffer)
                {
                    meanValueX += readingB.getX();
                    meanValueY += readingB.getY();
                    meanValueZ += readingB.getZ();
                }
                
                meanValueX /= buffer.size(); meanValueY /= buffer.size();
                meanValueZ /= buffer.size();
                
                reading.setNoGravityValues(reading.getX() - meanValueX, 
                        reading.getY() - meanValueY, 
                        reading.getZ() - meanValueZ);
                
                buffer.remove(0);
                buffer.add(reading);
            }
        }
    }
}
