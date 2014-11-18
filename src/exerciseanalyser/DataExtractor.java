package exerciseanalyser;

import java.util.ArrayList;
import models.Exercise;
import whereismysmartphoneanalyzer.SetOfExercisesForSpecificDestination;

/**
 *
 * @author matteo
 */
public class DataExtractor 
{
    protected final SetOfExercisesForSpecificDestination mSetOfExercises;
    protected ArrayList<ExerciseAnalyser> listExerciseAnalysers = new ArrayList<>();
    protected final String destination;
    
    public DataExtractor(ArrayList<Exercise> allExercises, String destination)
    {
        this.destination = destination;
        mSetOfExercises = new SetOfExercisesForSpecificDestination(allExercises, 
                destination);
    }
    
    public ArrayList<ExerciseAnalyser> getListExerciseAnalyser()
    {
        return this.listExerciseAnalysers;
    }
    
    public String dataForAllExercises(boolean accelerometer, boolean accelerometerRotated,
            boolean accelerometerNoGravity, boolean accelerometerNoGravityRotated, 
            boolean linear, boolean linearRotated, boolean rotation, boolean gravity, 
            boolean gyroscope, boolean magneticField, boolean ambientTemperature, 
            boolean light, boolean pressure, boolean relativeHumidity)
    {
        String finalString = "";
        for (ExerciseAnalyser exercise: listExerciseAnalysers)
        {
            String featuresToString = "";
            if (accelerometer)
            {
                featuresToString = exercise.getAccelerometerData().featuresToString();
            }
            if (accelerometerRotated)
            {
                featuresToString = exercise.getAccelerometerRotatedData().featuresToString();
            }
            if (accelerometerNoGravity)
            {
                featuresToString = exercise.getAccelerometerNoGravityData().featuresToString();
            }
            if (accelerometerNoGravityRotated)
            {
                featuresToString = exercise.getAccelerometerNoGravityRotatedData().featuresToString();
            }
            if (linear)
            {
                featuresToString = exercise.getLinearData().featuresToString();
            }
            if (linearRotated)
            {
                featuresToString = exercise.getLinearRotatedData().featuresToString();
            }
            if (rotation)
            {
                featuresToString = exercise.getRotationData().featuresToString();
            }
            if (gravity)
            {
                featuresToString = exercise.getGravityData().featuresToString();
            }
            if (gyroscope)
            {
                featuresToString = exercise.getGyroscopeData().featuresToString();
            }
            if (magneticField)
            {
                featuresToString = exercise.getMagneticFieldData().featuresToString();
            }
            if (ambientTemperature)
            {
                featuresToString = exercise.getAmbientTemperatureData().featuresToString();
            }
            if (light)
            {
                featuresToString = exercise.getLightData().featuresToString();
            }
            if (pressure)
            {
                featuresToString = exercise.getPressureData().featuresToString();
            }
            if (relativeHumidity)
            {
                featuresToString = exercise.getRelativeHumidityData().featuresToString();
            }
            
            if (!featuresToString.equals(""))
            {
                finalString += featuresToString + destination + System.getProperty("line.separator");
            }
        }
        return finalString;
    }
    
    public String getAccelerometerPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAccelerometerData().featuresNameToARFF();
    }
    
    public String getAccelerometerRotatedPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAccelerometerRotatedData().featuresNameToARFF();
    }
    
    public String getAccelerometerNoGravityPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAccelerometerNoGravityData().featuresNameToARFF();
    }
    
    public String getAccelerometerNoGravityRotatedPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAccelerometerNoGravityRotatedData().featuresNameToARFF();
    }
    
    public String getLinearPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getLinearData().featuresNameToARFF();
    }
    
    public String getLinearRotatedPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getLinearRotatedData().featuresNameToARFF();
    }
    
    public String getRotationPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getRotationData().featuresNameToARFF();
    }
    
    public String getGravityPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getGravityData().featuresNameToARFF();
    }
    
    public String getGyroscopePrologueARFF()
    {
        return listExerciseAnalysers.get(0).getGyroscopeData().featuresNameToARFF();
    }
    
    public String getMagneticFieldPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getGyroscopeData().featuresNameToARFF();
    }
    
    public String getAmbientTemperaturePrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAmbientTemperatureData().featuresNameToARFF();
    }
    
    public String getLightPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getLightData().featuresNameToARFF();
    }
    
    public String getPressurePrologueARFF()
    {
        return listExerciseAnalysers.get(0).getPressureData().featuresNameToARFF();
    }
    
    public String getRelativeHumidityPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getRelativeHumidityData().featuresNameToARFF();
    }
}
