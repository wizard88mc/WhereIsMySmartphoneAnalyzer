/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whereismysmartphoneanalyzer;

import java.util.ArrayList;
import models.Exercise;
import models.Reading;

/**
 *
 * @author Matteo Ciman
 */
public class DataExtractorOnlyDataBefore 
{
    private SetOfExercisesForSpecificDestination mSetOfExercises;
    private ArrayList<ExerciseAnalyserBefore> listExerciseAnalysesBefore = 
            new ArrayList<>();
    private final String destination;
    
    public DataExtractorOnlyDataBefore(ArrayList<Exercise> allExercises, String destination,
            long bufferDuration, int frequency)
    {
        mSetOfExercises = new SetOfExercisesForSpecificDestination(allExercises, 
                destination);
        this.destination = destination;
        
        for (Exercise exercise: mSetOfExercises.getExercises())
        {
            listExerciseAnalysesBefore.add(new ExerciseAnalyserBefore(bufferDuration, 
                    exercise.getReadingsAccelerometer(), 
                    exercise.getReadingsLinear(), frequency));
        }
    }
    
    
    public String dataForAllExercises(boolean accelerometer, boolean accelerometerRotated,
            boolean accelerometerNoGravity, boolean accelerometerNoGravityRotated, 
            boolean linear, boolean linearRotated, boolean rotation, boolean gravity, 
            boolean gyroscope, boolean magneticField, boolean ambientTemperature, 
            boolean light, boolean pressure, boolean relativeHumidity)
    {
        String finalString = "";
        for (ExerciseAnalyserBefore exercise: listExerciseAnalysesBefore)
        {
            if (accelerometer)
            {
                finalString += exercise.getAccelerometerData().featuresToString();
            }
            else if (accelerometerRotated)
            {
                finalString += exercise.getAccelerometerRotatedData().featuresToString();
            }
            else if (accelerometerNoGravity)
            {
                finalString += exercise.getAccelerometerNoGravityData().featuresToString();
            }
            else if (accelerometerNoGravityRotated)
            {
                finalString += exercise.getAccelerometerNoGravityRotatedData().featuresToString();
            }
            else if (linear)
            {
                finalString += exercise.getLinearData().featuresToString();
            }
            else if (linearRotated)
            {
                finalString += exercise.getLinearRotatedData().featuresToString();
            }
            else if (rotation)
            {
                finalString += exercise.getRotationData().featuresToString();
            }
            else if (gravity)
            {
                finalString += exercise.getGravityData().featuresToString();
            }
            else if (gyroscope)
            {
                finalString += exercise.getGyroscopeData().featuresToString();
            }
            else if (magneticField)
            {
                finalString += exercise.getMagneticFieldData().featuresToString();
            }
            else if (ambientTemperature)
            {
                finalString += exercise.getAmbientTemperatureData().featuresToString();
            }
            else if (light)
            {
                finalString += exercise.getLightData().featuresToString();
            }
            else if (pressure)
            {
                finalString += exercise.getPressureData().featuresToString();
            }
            else if (relativeHumidity)
            {
                finalString += exercise.getRelativeHumidityData().featuresToString();
            }
            
            finalString += destination + System.getProperty("line.separator");
        }
        return finalString;
    }
    
    public String accelerometerRotatedDataForAllExercises()
    {
        String finalString = "";
        for (ExerciseAnalyserBefore exercise: listExerciseAnalysesBefore)
        {
            finalString += exercise.getAccelerometerRotatedData().featuresToString()
                    + destination + System.getProperty("line.separator");
        }
        return finalString;
    }
    
    public String getAccelerometerPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getAccelerometerData().featuresNameToARFF();
    }
    
    public String getAccelerometerRotatedPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getAccelerometerRotatedData().featuresNameToARFF();
    }
    
    public String getAccelerometerNoGravityPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getAccelerometerNoGravityData().featuresNameToARFF();
    }
    
    public String getAccelerometerNoGravityRotatedPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getAccelerometerNoGravityRotatedData().featuresNameToARFF();
    }
    
    public String getLinearPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getLinearData().featuresNameToARFF();
    }
    
    public String getLinearRotatedPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getLinearRotatedData().featuresNameToARFF();
    }
    
    public String getRotationPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getRotationData().featuresNameToARFF();
    }
    
    public String getGravityPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getGravityData().featuresNameToARFF();
    }
    
    public String getGyroscopePrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getGyroscopeData().featuresNameToARFF();
    }
    
    public String getMagneticFieldPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getGyroscopeData().featuresNameToARFF();
    }
    
    public String getAmbientTemperaturePrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getAmbientTemperatureData().featuresNameToARFF();
    }
    
    public String getLightPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getLightData().featuresNameToARFF();
    }
    
    public String getPressurePrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getPressureData().featuresNameToARFF();
    }
    
    public String getRelativeHumidityPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getRelativeHumidityData().featuresNameToARFF();
    }
}
