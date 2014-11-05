/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whereismysmartphoneanalyzer;

import java.util.ArrayList;
import java.util.List;
import models.Exercise;
import models.Reading;

/**
 *
 * @author Matteo Ciman
 */
public class DataWorker 
{
    
    private static final int bufferDuration = 500000000; // 500 ms

    /**
     * Takes the accelerometer and linear readings of an exercise and adds them
     * to the exercise
     * @param exercise: the exercise to search readings for
     * @param readingsAccelerometer: all the accelerometer readings
     * @param readingsLinear: all the linear readings
     */
    private static void addReadingsToExercise(Exercise exercise, 
            ArrayList<Reading> readingsAccelerometer, 
            ArrayList<Reading> readingsLinear)
    {
        int trunkIdAccelerometer = exercise.getTrunkAccelerometer(),
            trunkIdLinear = exercise.getTrunkLinear();
        
        ArrayList<Reading> readingsAccelerometerForExercise = 
                extractReadingsForID(trunkIdAccelerometer, readingsAccelerometer),
                readingsLinearForExercise = extractReadingsForID(trunkIdLinear, 
                        readingsLinear);
        
        exercise.addReadings(readingsAccelerometerForExercise, 
                readingsLinearForExercise);
    }
    
    /**
     * Searches for all the readings with a particular ID
     * @param trunkID the ID of the trunk to search for
     * @param readings the readings to search in
     * @return an ArrayList of readings 
     */
    private static ArrayList<Reading> extractReadingsForID(int trunkID, 
            ArrayList<Reading> readings)
    {
        
        int startIndex = -1, endIndex = -1; boolean end = false;
        try 
        {
        for (int i = 0; i < readings.size() && !end; i++)
        {
            if (readings.get(i).getTrunkId() == trunkID)
            {
                if (startIndex == -1)
                {
                    startIndex = i;
                }
            }
            else 
            {
                if (startIndex != -1) 
                {
                    endIndex = i-1;
                    end = true;
                }
            }
        }
        
        if (endIndex == -1)
        {
            // The last trunk necessary to manually set the final index of the readings
            endIndex = readings.size() - 1;
        }
        
        return new ArrayList<>(readings.subList(startIndex, endIndex + 1)); 
        }
        catch(Exception exc)
        {
            System.out.println(exc.toString());
            return null;
        }
    }
    
    /**
     * Iterates on all the exercises to add to them the readings
     * @param exercises: all the exercises
     * @param readingsAccelerometer: all the readings of the accelerometer
     * @param readingsLinear: all the readings of the linear
     */
    public static void addReadingsToAllExercises(ArrayList<Exercise> exercises,
            ArrayList<Reading> readingsAccelerometer, 
            ArrayList<Reading> readingsLinear)
    {
        for (Exercise exercise: exercises)
        {
            DataWorker.addReadingsToExercise(exercise, readingsAccelerometer, 
                    readingsLinear);
            
            removeGravityFromAccelerometerData(exercise.getReadingsAccelerometer());
            
            exercise.rotateReadings();
        }
    }
    
    protected static void removeGravityFromAccelerometerData(
            ArrayList<Reading> accelerometer)
    {
        List<Reading> buffer = new ArrayList<Reading>();
        boolean bufferFull = false;
        
        for (Reading reading: accelerometer)
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
