/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whereismysmartphoneanalyzer;

import features.AmbientTemperatureData;
import features.LightData;
import features.PressureData;
import features.RelativeHumidityData;
import features.fouraxesdata.AccelerometerData;
import features.fouraxesdata.AccelerometerNoGravityData;
import features.fouraxesdata.AccelerometerNoGravityRotatedData;
import features.fouraxesdata.AccelerometerRotatedData;
import features.fouraxesdata.LinearData;
import features.fouraxesdata.LinearRotatedData;
import features.threeaxesdata.GravityData;
import features.threeaxesdata.GyroscopeData;
import features.threeaxesdata.MagneticFieldData;
import features.threeaxesdata.RotationData;
import java.util.ArrayList;
import models.Reading;

/**
 *
 * @author Matteo Ciman
 */
public class ExerciseAnalyserBefore 
{
    private long bufferSize;
    private ArrayList<Reading> readings;
    private ArrayList<Reading> readingsLinear;
    private ArrayList<Reading> buffer;
    private ArrayList<Reading> bufferLinear;
    private long minDistanceBetweenTwoReadings;
    
    private AccelerometerData mAccelerometerData;
    private AccelerometerNoGravityData mAccelerometerNoGravityData;
    private AccelerometerNoGravityRotatedData mAccelerometerNoGravityRotatedData;
    private AccelerometerRotatedData mAccelerometerRotatedData;
    private LinearData mLinearData;
    private LinearRotatedData mLinearRotatedData;
    private RotationData mRotationData;
    private GravityData mGravityData;
    private GyroscopeData mGyroscopeData;
    private MagneticFieldData mMagneticFieldData;
    private AmbientTemperatureData mAmbientTemperatureData;
    private LightData mLightData;
    private PressureData mPressureData;
    private RelativeHumidityData mRelativeHumidityData;
    
    public ExerciseAnalyserBefore(long bufferSize, ArrayList<Reading> readings, 
            ArrayList<Reading> readingsLinear, int frequency)
    {
        this.bufferSize = bufferSize; this.readings = readings;
        this.readingsLinear = readingsLinear;
        this.minDistanceBetweenTwoReadings = 100000000 / frequency; // min distance in nano seconds
        
        int firstIndex = findIndexFirstDataWithProximityValueEqualZero();
        populateBuffer(firstIndex);
    }
    
    /**
     * Finds the index of the first Reading that has the proximity value = 0
     * (is the first value where the smartphone is at the target location)
     * @return the index of the first Reading object, -1 if no one is found
     */
    private int findIndexFirstDataWithProximityValueEqualZero()
    {
        for (int i = 0; i < readings.size(); i++)
        {
            if (readings.get(i).getProximityValue() == 0)
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Adds all the objects to the buffer depending of the max allowed size 
     * of the buffer (time length)
     * @param indexFirstObject the index of the first Reading with the smartphone
     * at target location
     */
    private void populateBuffer(int indexFirstObject)
    {
        this.buffer = new ArrayList<>();
        
        boolean bufferFull = false;
        buffer.add(readings.get(indexFirstObject - 1));
        for (int i = indexFirstObject - 2; i >= 0 && !bufferFull; i--)
        {
            // Checks if the current readings is still inside the buffer or not
            if (buffer.get(buffer.size() - 1).getTimestamp() - 
                    readings.get(i).getTimestamp() > bufferSize)
            {
                // The reading is outside the buffer. Time to stop
                bufferFull = true;
            }
            else
            {
                // Adding Reading to the head of the buffer
                buffer.add(0, readings.get(i));
            }
        }
        
        /**
         * Based on the required frequency, we remove all data not correct
         * for the requested frequency
         */
        for (int i = 1; i < buffer.size();)
        {
            if (buffer.get(i).getTimestamp() - buffer.get(i-1).getTimestamp() < 
                    minDistanceBetweenTwoReadings)
            {
                buffer.remove(i);
            }
            else
            {
                i++;
            }
        }
    }
    
    /**
     * Creates the Accelerometer data
     */
    private void createAccelerometerData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getX()); y.add(reading.getY()); z.add(reading.getX());
        }
        
        mAccelerometerData = new AccelerometerData(x, y, z);
    }
    
    /**
     * Creates the AccelerometerRotated data
     */
    private void createAccelerometerRotatedData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getRotatedX()); y.add(reading.getRotatedY());
            z.add(reading.getRotatedZ());
        }
        
        mAccelerometerRotatedData = new AccelerometerRotatedData(x, y, z);
    }
    
    /**
     * Creates the AccelerometerNoGravity data
     */
    private void createAccelerometerNoGravityData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getNoGravityX()); y.add(reading.getNoGravityY());
            z.add(reading.getNoGravityZ());
        }
        
        mAccelerometerNoGravityData = new AccelerometerNoGravityData(x, y, z);
    }
    
    private void createAccelerometerNoGravityRotatedData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getNoGravityRotatedX()); y.add(reading.getNoGravityRotatedY());
            z.add(reading.getNoGravityRotatedZ());
        }
        
        mAccelerometerNoGravityRotatedData = 
                new AccelerometerNoGravityRotatedData(x, y, z);
    }
    
    /**
     * Creates the Linear data
     */
    private void createLinearData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: bufferLinear)
        {
            x.add(reading.getX()); y.add(reading.getY()); z.add(reading.getZ());
        }
        
        mLinearData = new LinearData(x, y, z);
    }
    
    /**
     * Creates the LinearRotated data
     */
    private void createLinearRotatedData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: bufferLinear)
        {
            x.add(reading.getRotatedX()); y.add(reading.getRotatedY());
            z.add(reading.getRotatedZ());
        }
        
        mLinearRotatedData = new LinearRotatedData(x, y, z);
    }
}
