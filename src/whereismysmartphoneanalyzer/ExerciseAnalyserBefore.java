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
    private final long minDistanceBetweenTwoReadings;
    
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
        this.bufferSize = bufferSize * 1000 * 1000; this.readings = readings;
        this.readingsLinear = readingsLinear;
        this.minDistanceBetweenTwoReadings = 100000000 / frequency; // min distance in nano seconds
        
        int firstIndex = findIndexFirstDataWithProximityValueEqualZero(false);
        populateBuffer(firstIndex, false);
        
        firstIndex = findIndexFirstDataWithProximityValueEqualZero(true);
        populateBuffer(firstIndex, true);
        
        createAccelerometerData(); createAccelerometerRotatedData();
        createAccelerometerNoGravityData(); createAccelerometerNoGravityRotatedData();
        createLinearData(); createLinearRotatedData();
        createRotationData(); createGravityData(); createGyroscopeData();
        createMagneticFieldData(); createAmbientTemperatureData();
        createPressureData(); createLightData(); createRelativeHumidityData();
        
        mAccelerometerData.calculateFeatures(); mAccelerometerRotatedData.calculateFeatures();
        
    }
    
    /**
     * Finds the index of the first Reading that has the proximity value = 0
     * (is the first value where the smartphone is at the target location)
     * @return the index of the first Reading object, -1 if no one is found
     */
    private int findIndexFirstDataWithProximityValueEqualZero(boolean linear)
    {
        ArrayList<Reading> readingsToUse = readings;
        if (linear)
        {
            readingsToUse = readingsLinear;
        }
        if (readingsToUse != null)
        {
        for (int i = 0; i < readingsToUse.size(); i++)
            {
                if ((readingsToUse.get(i) != null) && readingsToUse.get(i).getProximityValue() == 0)
                {
                    return i;
                }
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
    private void populateBuffer(int indexFirstObject, boolean linear)
    {
        ArrayList<Reading> bufferToUse = null, readingsToUse = null;
        if (!linear)
        {
            this.buffer = new ArrayList<>();
            bufferToUse = this.buffer; readingsToUse = readings;
        }
        else
        {
            this.bufferLinear = new ArrayList<>();
            bufferToUse = this.bufferLinear; readingsToUse = this.readingsLinear;
        }
        
        if (readingsToUse != null)
        {
            boolean bufferFull = false;
            bufferToUse.add(readingsToUse.get(indexFirstObject - 1));
            for (int i = indexFirstObject - 2; i >= 0 && !bufferFull; i--)
            {
                // Checks if the current readings is still inside the buffer or not
                if (bufferToUse.get(bufferToUse.size() - 1).getTimestamp() - 
                        readingsToUse.get(i).getTimestamp() > bufferSize)
                {
                    // The reading is outside the buffer. Time to stop
                    bufferFull = true;
                }
                else
                {
                    // Adding Reading to the head of the buffer
                    bufferToUse.add(0, readingsToUse.get(i));
                }
            }

            /**
             * Based on the required frequency, we remove all data not correct
             * for the requested frequency
             */
            for (int i = 1; i < bufferToUse.size();)
            {
                if (bufferToUse.get(i).getTimestamp() - bufferToUse.get(i-1).getTimestamp() < 
                        minDistanceBetweenTwoReadings)
                {
                    bufferToUse.remove(i);
                }
                else
                {
                    i++;
                }
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
    
    /**
     * Creates the Rotation data
     */
    private void createRotationData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getRotationX()); y.add(reading.getRotationY());
            z.add(reading.getRotationZ());
        }
        
        mRotationData = new RotationData(x, y, z);
    }
    
    /**
     * Creates the Gravity data
     */
    private void createGravityData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getGravityX()); y.add(reading.getGravityY());
            z.add(reading.getGravityZ());
        }
        
        mGravityData = new GravityData(x, y, z);
    }
    
    /**
     * Creates the Gyroscope data
     */
    private void createGyroscopeData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getGyroscopeX()); y.add(reading.getGyroscopeY());
            z.add(reading.getGyroscopeZ());
        }
        
        mGyroscopeData = new GyroscopeData(x, y, z);
    }
    
    /**
     * Creates the Magnetic Field data
     */
    private void createMagneticFieldData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getMagneticFieldX()); y.add(reading.getMagneticFieldY());
            z.add(reading.getMagneticFieldZ());
        }
        
        mMagneticFieldData = new MagneticFieldData(x, y, z);
    }
    
    /**
     * Creates the Ambient Temperature data
     */
    private void createAmbientTemperatureData()
    {
        ArrayList<Float> x = new ArrayList<>();
        Float max = null;
        
        for (Reading reading: buffer)
        {
            x.add(reading.getAmbientTemperature());
            if (max == null)
            {
                max = reading.getMaxAmbientTemperature();
            }
        }
        
        mAmbientTemperatureData = new AmbientTemperatureData(x, max);
    }
    
    /**
     * Creates the Light data
     */
    private void createLightData()
    {
        ArrayList<Float> x = new ArrayList<>();
        Float max = null;
        
        for (Reading reading: buffer)
        {
            x.add(reading.getLight());
            if (max == null)
            {
                max = reading.getMaxLight();
            }
        }
        
        mLightData = new LightData(x, max);
    }
    
    /**
     * Creates Pressure data
     */
    private void createPressureData()
    {
        ArrayList<Float> x = new ArrayList<>();
        Float max = null;
        
        for (Reading reading: buffer)
        {
            x.add(reading.getPressure());
            if (max == null)
            {
                max = reading.getMaxPressure();
            }
        }
        
        mPressureData = new PressureData(x, max);
    }
    
    /**
     * Creates the Relative Humidity data
     */
    private void createRelativeHumidityData()
    {
        ArrayList<Float> x = new ArrayList<>();
        Float max = null;
        
        for (Reading reading: buffer)
        {
            x.add(reading.getRelativeHumidity());
            if (max == null)
            {
                max = reading.getMaxRelativeHumidity();
            }
        }
        
        mRelativeHumidityData = new RelativeHumidityData(x, max);
    }
    
    public AccelerometerData getAccelerometerData()
    {
        return this.mAccelerometerData;
    }
    
    public AccelerometerRotatedData getAccelerometerRotatedData()
    {
        return this.mAccelerometerRotatedData;
    }
    
    public AccelerometerNoGravityData getAccelerometerNoGravityData()
    {
        return this.mAccelerometerNoGravityData;
    }
    
    public AccelerometerNoGravityRotatedData getAccelerometerNoGravityRotatedData()
    {
        return this.mAccelerometerNoGravityRotatedData;
    }
    
    public LinearData getLinearData()
    {
        return this.mLinearData;
    }
    
    public LinearRotatedData getLinearRotatedData()
    {
        return this.mLinearRotatedData;
    }
    
    public RotationData getRotationData()
    {
        return this.mRotationData;
    }
    
    public GravityData getGravityData()
    {
        return this.mGravityData;
    }
    
    public GyroscopeData getGyroscopeData()
    {
        return this.mGyroscopeData;
    }
    
    public MagneticFieldData getMagneticFieldData()
    {
        return this.mMagneticFieldData;
    }
    
    public AmbientTemperatureData getAmbientTemperatureData()
    {
        return this.mAmbientTemperatureData;
    }
    
    public LightData getLightData()
    {
        return this.mLightData;
    }
    
    public PressureData getPressureData()
    {
        return this.mPressureData;
    }
    
    public RelativeHumidityData getRelativeHumidityData()
    {
        return this.mRelativeHumidityData;
    }
}
