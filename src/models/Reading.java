
package models;

/**
 * class Reading
 * 
 * This class holds all the information recorded from the smartphone
 * 
 * @author Matteo Ciman
 * @version 0.1
 * @since 2014-10-20
 */
public class Reading 
{   
    private int trunkID;
    private long timestamp;
    private int proximity;
    private Float x;
    private Float y;
    private Float z;
    private Float noGravityX = null;
    private Float noGravityY = null;
    private Float noGravityZ = null;
    private Float rotatedX = null;
    private Float rotatedY = null;
    private Float rotatedZ = null;
    private Float rotatedNoGravityX = null;
    private Float rotatedNoGravityY = null;
    private Float rotatedNoGravityZ = null;
    private Float rotationX;
    private Float rotationY;
    private Float rotationZ;
    private Float gravityX = null;
    private Float gravityY = null;
    private Float gravityZ = null;
    private Float gyroscopeX = null;
    private Float gyroscopeY = null;
    private Float gyroscopeZ = null;
    private Float magneticFieldX = null;
    private Float magneticFieldY = null;
    private Float magneticFieldZ = null;
    private Float ambientTemperature = null;
    private Float maxAmbientTemperature = null;
    private Float light = null;
    private Float maxLight = null;
    private Float pressure = null;
    private Float maxPressure = null;
    private Float relativeHumidity = null;
    private Float maxRelativeHumidity = null;
    
    /**
     * Creates a new Reading object with the provided data
     * 
     * @param trunkID
     * @param timestamp
     * @param proximity
     * @param x: accelerometer or linear sensor x data
     * @param y: accelerometer or linear sensor y data
     * @param z: accelerometer or linear sensor z data
     * @param rotationX
     * @param rotationY
     * @param rotationZ
     * @param gravityX
     * @param gravityY
     * @param gravityZ
     * @param gyroscopeX
     * @param gyroscopeY
     * @param gyroscopeZ
     * @param magneticFieldX
     * @param magneticFieldY
     * @param magneticFieldZ
     * @param ambientTemperature
     * @param maxAmbientTemperature
     * @param light
     * @param maxLight
     * @param pressure
     * @param maxPressure
     * @param relativeHumidity
     * @param maxRelativeHumidity 
     */
    protected final void buildReading(int trunkID, long timestamp, int proximity, float x, 
            float y, float z, float rotationX, float rotationY, float rotationZ,
            Float gravityX, Float gravityY, Float gravityZ, Float gyroscopeX, 
            Float gyroscopeY, Float gyroscopeZ, Float magneticFieldX, 
            Float magneticFieldY, Float magneticFieldZ, Float ambientTemperature, 
            Float maxAmbientTemperature, Float light, Float maxLight, 
            Float pressure, Float maxPressure, Float relativeHumidity, 
            Float maxRelativeHumidity) 
    {
        this.trunkID = trunkID; this.proximity = proximity;
        this.timestamp = timestamp; this.x = x; this.y = y; this.z = z;
        this.rotationX = rotationX; this.rotationY = rotationY; 
        this.rotationZ = rotationZ; this.gravityX = gravityX;
        this.gravityY = gravityY; this.gravityZ = gravityZ;
        this.gyroscopeX = gyroscopeX; this.gyroscopeY = gyroscopeY;
        this.gyroscopeZ = gyroscopeZ; this.magneticFieldX = magneticFieldX;
        this.magneticFieldY = magneticFieldY; this.magneticFieldZ = magneticFieldZ;
        this.ambientTemperature = ambientTemperature; 
        this.maxAmbientTemperature = maxAmbientTemperature;
        this.light = light; this.maxLight = maxLight; this.pressure = pressure;
        this.maxPressure = maxPressure; this.relativeHumidity = relativeHumidity;
        this.maxRelativeHumidity = relativeHumidity;
    }
    
    public Reading(String reading)
    {
        String[] elements = reading.replace("(", "").replace(")", "").split(",");
        /**
         * elements[0]: trunkID
         * elements[1]: timestamp
         * elements[2]: proximity
         * elements[3]: x
         * elements[4]: y
         * elements[5]: z
         * elements[6]: rotationX
         * elements[7]: rotationY
         * elements[8]: rotationZ
         * elements[9]: gravityX
         * elements[10]: gravityY
         * elements[11]: gravityZ
         * elements[12]: gyroscopeX
         * elements[13]: gyroscopeY
         * elements[14]: gyroscopeZ
         * elements[15]: magneticFieldX
         * elements[16]: magneticFieldY
         * elements[17]: magneticFieldZ
         * elements[18]: ambientTemperature
         * elements[19]: maxAmbientTemperature
         * elements[20]: light
         * elements[21]: maxLight
         * elements[22]: pressure
         * elements[23]: maxPressure
         * elements[24]: relativeHumidity
         * elements[25]: maxRelativeHumidity
         */
        
        this.buildReading(Integer.valueOf(elements[0]), 
                Double.valueOf(elements[1]).longValue(), 
                Float.valueOf(elements[2]).intValue(), Float.valueOf(elements[3]), 
                Float.valueOf(elements[4]), Float.valueOf(elements[5]), 
                Float.valueOf(elements[6]), Float.valueOf(elements[7]),
                Float.valueOf(elements[8]),
                elements[9].equals("null") ? null : Float.valueOf(elements[9]),
                elements[10].equals("null") ? null : Float.valueOf(elements[10]),
                elements[11].equals("null") ? null : Float.valueOf(elements[11]),
                elements[12].equals("null") ? null : Float.valueOf(elements[12]),
                elements[13].equals("null") ? null : Float.valueOf(elements[13]),
                elements[14].equals("null") ? null : Float.valueOf(elements[14]),
                elements[15].equals("null") ? null : Float.valueOf(elements[15]),
                elements[16].equals("null") ? null : Float.valueOf(elements[16]),
                elements[17].equals("null") ? null : Float.valueOf(elements[17]),
                elements[18].equals("null") ? null : Float.valueOf(elements[18]),
                elements[19].equals("null") ? null : Float.valueOf(elements[19]),
                elements[20].equals("null") ? null : Float.valueOf(elements[20]),
                elements[21].equals("null") ? null : Float.valueOf(elements[21]),
                elements[22].equals("null") ? null : Float.valueOf(elements[22]),
                elements[23].equals("null") ? null : Float.valueOf(elements[23]),
                elements[24].equals("null") ? null : Float.valueOf(elements[24]),
                elements[25].equals("null") ? null : Float.valueOf(elements[25]));
    }
    
    /**
     * Returns the trunk ID of the reading
     * @return the trunk ID
     */
    public int getTrunkId()
    {
        return this.trunkID;
    }
    
    public long getTimestamp()
    {
        return this.timestamp;
    }
    
    public Float getX()
    {
        return this.x;
    }
    
    public Float getY()
    {
        return this.y;
    }
    
    public Float getZ()
    {
        return this.z;
    }
    
    public Float getRotatedX()
    {
        return this.rotatedX;
    }
    
    public Float getRotatedY()
    {
        return this.rotatedY;
    }
    
    public Float getRotatedZ()
    {
        return this.rotatedZ;
    }
    
    public Float getNoGravityX()
    {
        return this.noGravityX;
    }
    
    public Float getNoGravityY()
    {
        return this.noGravityY;
    }
    
    public Float getNoGravityZ()
    {
        return this.noGravityZ;
    }
    
    public Float getNoGravityRotatedX()
    {
        return this.rotatedNoGravityX;
    }
    
    public Float getNoGravityRotatedY()
    {
        return this.rotatedNoGravityY;
    }
    
    public Float getNoGravityRotatedZ()
    {
        return this.rotatedNoGravityZ;
    }
    
    public int getProximityValue()
    {
        return this.proximity;
    }
    
    public Float getRotationX()
    {
        return this.rotationX;
    }
    
    public Float getRotationY()
    {
        return this.rotationY;
    }
    
    public Float getRotationZ()
    {
        return this.rotationZ;
    }
    
    public Float getGravityX()
    {
        return this.gravityX;
    }
    
    public Float getGravityY()
    {
        return this.gravityY;
    }
    
    public Float getGravityZ()
    {
        return this.gravityZ;
    }
    
    public Float getGyroscopeX()
    {
        return this.gyroscopeX;
    }
    
    public Float getGyroscopeY()
    {
        return this.gyroscopeY;
    }
    
    public Float getGyroscopeZ()
    {
        return this.gyroscopeZ;
    }
    
    public Float getMagneticFieldX()
    {
        return this.magneticFieldX;
    }
    
    public Float getMagneticFieldY()
    {
        return this.magneticFieldY;
    }
    
    public Float getMagneticFieldZ()
    {
        return this.magneticFieldZ;
    }
    
    public Float getAmbientTemperature()
    {
        return this.ambientTemperature;
    }
    
    public Float getMaxAmbientTemperature()
    {
        return this.maxAmbientTemperature;
    }
    
    public Float getLight()
    {
        return this.light;
    }
    
    public Float getMaxLight()
    {
        return this.maxLight;
    }
    
    public Float getPressure()
    {
        return this.maxPressure;
    }
    
    public Float getMaxPressure()
    {
        return this.maxPressure;
    }
    
    public Float getRelativeHumidity()
    {
        return this.relativeHumidity;
    }
    
    public Float getMaxRelativeHumidity()
    {
        return this.maxRelativeHumidity;
    }
    
    /**
     * Sets the value rotated for the accelerometer data
     * @param x: the new x rotated
     * @param y: the new y rotated
     * @param z: the new z rotated
     */
    public void setNoGravityValues(float x, float y, float z)
    {
        this.noGravityX = x; this.noGravityY = y; this.noGravityZ = z;
    }
    
    /**
     * Called on accelerometer and linear data
     */
    public void rotateBasicValues()
    {
        rotateValues(false);
    }
    
    /**
     * Called only on accelerometer data
     */
    public void rotateNoGravityValues()
    {
        rotateValues(true);
    }
    
    /**
     * Using the rotation sensor values, it rotates the readings to a fixed
     * coordinate system
     * @param noGravity is the method has to rotate the noGravity values or
     * the basic ones
     */
    private void rotateValues(boolean noGravity)
    {
        if ((!noGravity && x != null && y != null && z != null) || (noGravity && noGravityX != null && noGravityY != null 
                && noGravityZ != null))
        {
            double norm = Math.sqrt(Math.pow(rotationX, 2) + Math.pow(rotationY, 2)
                + Math.pow(rotationZ, 2));
            if (norm > 1)
            {
                norm = 1;
            }
            double alpha = 2 * Math.asin(norm);
            double xAngle = rotationX / norm, yAngle = rotationY / norm, 
                    zAngle = rotationZ / norm;
            double xAngleSquare = Math.pow(xAngle, 2), 
                    yAngleSquare = Math.pow(yAngle, 2),
                    zAngleSquare = Math.pow(zAngle, 2);

            double sinAlpha = Math.sin(alpha), cosAlpha = Math.cos(alpha);

            double xFirst = x, yFirst = y, zFirst = z;
            if (noGravity)
            {
                xFirst = noGravityX; yFirst = noGravityY; zFirst = noGravityZ;
            }

            double calculatedX = ((xAngleSquare + (1 - xAngleSquare) * cosAlpha) * xFirst +
                    (((1 - cosAlpha) * xAngle * yAngle) - sinAlpha * zAngle) * yFirst +
                    (((1 - cosAlpha) * xAngle * zAngle) + sinAlpha * yAngle) * zFirst);

            double calculatedY = ((((1 - cosAlpha) * yAngle * xAngle) + sinAlpha * zAngle) * xFirst +
                    (yAngleSquare + (1 - yAngleSquare) * cosAlpha) * yFirst +
                    (((1 - cosAlpha) * yAngle * zAngle) - sinAlpha * xAngle) * zFirst);

            double calculatedZ = ((((1 - cosAlpha) * zAngle * xAngle) - sinAlpha * yAngle) * xFirst +
                            ((1 - cosAlpha) * zAngle * yAngle + sinAlpha * xAngle) * yFirst +
                            (zAngleSquare + (1 - zAngleSquare) * cosAlpha) * zFirst);

            if (noGravity)
            {
                rotatedNoGravityX = Double.valueOf(calculatedX).floatValue(); 
                rotatedNoGravityY = Double.valueOf(calculatedY).floatValue();
                rotatedNoGravityZ = Double.valueOf(calculatedZ).floatValue();
            }
            else 
            {
                rotatedX = Double.valueOf(calculatedX).floatValue();
                rotatedY = Double.valueOf(calculatedY).floatValue();
                rotatedZ = Double.valueOf(calculatedZ).floatValue();
            }
        }
        else
        {
            //System.out.println("Some null values");
        }
    }
    
    /**
     * Sets the new time for the reading
     * @param newTime the new time of the sample
     */
    public void setTime(long newTime)
    {
        this.timestamp = newTime;
    }
    
    /**
     * Sets the new value for the proximity sensor, used to correct some 
     * sensor errors
     * @param value the new value
     */
    public void setProximityValue(int value)
    {
        this.proximity = value;
    }
}
