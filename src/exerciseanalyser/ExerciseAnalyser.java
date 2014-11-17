package exerciseanalyser;

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
 * @author matteo
 */
public class ExerciseAnalyser 
{
    protected long bufferSize;
    protected ArrayList<Reading> readings;
    protected ArrayList<Reading> readingsLinear;
    protected ArrayList<Reading> buffer;
    protected ArrayList<Reading> bufferLinear;
    protected final long minDistanceBetweenTwoReadings;
    
    protected AccelerometerData mAccelerometerData;
    protected AccelerometerNoGravityData mAccelerometerNoGravityData;
    protected AccelerometerNoGravityRotatedData mAccelerometerNoGravityRotatedData;
    protected AccelerometerRotatedData mAccelerometerRotatedData;
    protected LinearData mLinearData;
    protected LinearRotatedData mLinearRotatedData;
    protected RotationData mRotationData;
    protected GravityData mGravityData;
    protected GyroscopeData mGyroscopeData;
    protected MagneticFieldData mMagneticFieldData;
    protected AmbientTemperatureData mAmbientTemperatureData;
    protected LightData mLightData;
    protected PressureData mPressureData;
    protected RelativeHumidityData mRelativeHumidityData;
    
    public ExerciseAnalyser(long bufferSize, ArrayList<Reading> readings, 
            ArrayList<Reading> readingsLinear, int frequency)
    {
        this.bufferSize = bufferSize * 1000 * 1000; this.readings = readings;
        this.readingsLinear = readingsLinear;
        this.minDistanceBetweenTwoReadings = 100000000 / frequency; // min distance in nano seconds
    }
    
    /**
     * Finds the index of the first Reading that has the proximity value = 0
     * (is the first value where the smartphone is at the target location)
     * @return the index of the first Reading object, -1 if no one is found
     */
    protected int findIndexFirstDataWithProximityValueEqualZero(boolean linear)
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
     * Creates the Accelerometer data
     */
    protected void createAccelerometerData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getX()); y.add(reading.getY()); z.add(reading.getX());
        }
        
        mAccelerometerData = new AccelerometerData(x, y, z);
        mAccelerometerData.calculateFeatures();
    }
    
    /**
     * Creates the AccelerometerRotated data
     */
    protected void createAccelerometerRotatedData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getRotatedX()); y.add(reading.getRotatedY());
            z.add(reading.getRotatedZ());
        }
        
        mAccelerometerRotatedData = new AccelerometerRotatedData(x, y, z);
        mAccelerometerRotatedData.calculateFeatures();
    }
    
    /**
     * Creates the AccelerometerNoGravity data
     */
    protected void createAccelerometerNoGravityData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getNoGravityX()); y.add(reading.getNoGravityY());
            z.add(reading.getNoGravityZ());
        }
        
        mAccelerometerNoGravityData = new AccelerometerNoGravityData(x, y, z);
        mAccelerometerNoGravityData.calculateFeatures();
    }
    
    protected void createAccelerometerNoGravityRotatedData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            if (reading.getNoGravityRotatedX() != null)
            {
                x.add(reading.getNoGravityRotatedX()); y.add(reading.getNoGravityRotatedY());
                z.add(reading.getNoGravityRotatedZ());
            }
            else
            {
                System.out.println("null");
            }
        }
        
        mAccelerometerNoGravityRotatedData = 
                new AccelerometerNoGravityRotatedData(x, y, z);
        mAccelerometerNoGravityRotatedData.calculateFeatures();
    }
    
    /**
     * Creates the Linear data
     */
    protected void createLinearData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: bufferLinear)
        {
            x.add(reading.getX()); y.add(reading.getY()); z.add(reading.getZ());
        }
        
        mLinearData = new LinearData(x, y, z);
        mLinearData.calculateFeatures();
    }
    
    /**
     * Creates the LinearRotated data
     */
    protected void createLinearRotatedData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: bufferLinear)
        {
            x.add(reading.getRotatedX()); y.add(reading.getRotatedY());
            z.add(reading.getRotatedZ());
        }
        
        mLinearRotatedData = new LinearRotatedData(x, y, z);
        mLinearRotatedData.calculateFeatures();
    }
    
    /**
     * Creates the Rotation data
     */
    protected void createRotationData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getRotationX()); y.add(reading.getRotationY());
            z.add(reading.getRotationZ());
        }
        
        mRotationData = new RotationData(x, y, z);
        mRotationData.calculateFeatures();
    }
    
    /**
     * Creates the Gravity data
     */
    protected void createGravityData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getGravityX()); y.add(reading.getGravityY());
            z.add(reading.getGravityZ());
        }
        
        mGravityData = new GravityData(x, y, z);
        mGravityData.calculateFeatures();
    }
    
    /**
     * Creates the Gyroscope data
     */
    protected void createGyroscopeData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getGyroscopeX()); y.add(reading.getGyroscopeY());
            z.add(reading.getGyroscopeZ());
        }
        
        mGyroscopeData = new GyroscopeData(x, y, z);
        mGyroscopeData.calculateFeatures();
    }
    
    /**
     * Creates the Magnetic Field data
     */
    protected void createMagneticFieldData()
    {
        ArrayList<Float> x = new ArrayList<>(), y = new ArrayList<>(), 
                z = new ArrayList<>();
        
        for (Reading reading: buffer)
        {
            x.add(reading.getMagneticFieldX()); y.add(reading.getMagneticFieldY());
            z.add(reading.getMagneticFieldZ());
        }
        
        mMagneticFieldData = new MagneticFieldData(x, y, z);
        mMagneticFieldData.calculateFeatures();
    }
    
    /**
     * Creates the Ambient Temperature data
     */
    protected void createAmbientTemperatureData()
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
        mAmbientTemperatureData.calculateFeatures();
    }
    
    /**
     * Creates the Light data
     */
    protected void createLightData()
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
        mLightData.calculateFeatures();
    }
    
    /**
     * Creates Pressure data
     */
    protected void createPressureData()
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
        mPressureData.calculateFeatures();
    }
    
    /**
     * Creates the Relative Humidity data
     */
    protected void createRelativeHumidityData()
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
        mRelativeHumidityData.calculateFeatures();
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
