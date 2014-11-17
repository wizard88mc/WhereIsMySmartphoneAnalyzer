package exerciseanalyser.analyserafter;

import exerciseanalyser.ExerciseAnalyser;
import java.util.ArrayList;
import models.Reading;

/**
 *
 * @author Matteo Ciman
 */
public class ExerciseAnalyserAfter extends ExerciseAnalyser
{
    public ExerciseAnalyserAfter(long bufferSize, ArrayList<Reading> readings, 
            ArrayList<Reading> readingsLinear, int frequency)
    {    
        super(bufferSize, readings, readingsLinear, frequency);
        
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
    }
    
    /**
     * Adds all the objects to the buffer depending of the max allowed size 
     * of the buffer (time length). Elements are added from the first with 
     * proximity value = 0 to the last one allowed
     * @param indexFirstObject the index of the first Reading with the smartphone
     * at target location
     */
    private void populateBuffer(int indexFirstObject, boolean linear)
    {
        ArrayList<Reading> bufferToUse, readingsToUse;
        if (!linear)
        {
            this.buffer = new ArrayList<>();
            bufferToUse = this.buffer; readingsToUse = this.readings;
        }
        else
        {
            this.bufferLinear = new ArrayList<>();
            bufferToUse = this.bufferLinear; readingsToUse = this.readingsLinear;
        }
        
        if (readingsToUse != null)
        {
            boolean bufferFull = false;
            bufferToUse.add(readingsToUse.get(indexFirstObject));
            for (int i = indexFirstObject + 1; i < readingsToUse.size() && !bufferFull; i++)
            {
                // Checks if the current readings is still inside the buffer or not
                if (readingsToUse.get(i).getTimestamp() - 
                        bufferToUse.get(0).getTimestamp() > bufferSize)
                {
                    // The reading is outside the buffer. Time to stop
                    bufferFull = true;
                }
                else
                {
                    // Adding Reading to the head of the buffer
                    bufferToUse.add(readingsToUse.get(i));
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
}
