
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
    private float proximity;
    private float x;
    private float y;
    private float z;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
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
    protected final void buildReading(int trunkID, long timestamp, float proximity, float x, 
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
                Float.valueOf(elements[2]), Float.valueOf(elements[3]), 
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
}
