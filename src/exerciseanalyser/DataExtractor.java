package exerciseanalyser;

import java.util.ArrayList;
import java.util.HashMap;
import models.Exercise;
import whereismysmartphoneanalyzer.SetOfExercisesForSpecificDestinationAndAction;

/**
 *
 * @author matteo
 */
public class DataExtractor 
{
    protected final SetOfExercisesForSpecificDestinationAndAction mSetOfExercises;
    protected ArrayList<ExerciseAnalyser> listExerciseAnalysers = 
            new ArrayList<ExerciseAnalyser>();
    protected final String destination;
    protected String destinationForOutput;
    protected final String activity;
    
    public DataExtractor(ArrayList<Exercise> allExercises, String activity, 
            String destination)
    {
        this.destination = destination; this.activity = activity;
        this.destinationForOutput = destination;
        mSetOfExercises = new SetOfExercisesForSpecificDestinationAndAction(allExercises, 
                activity, destination);
    }
    
    public ArrayList<ExerciseAnalyser> getListExerciseAnalyser()
    {
        return this.listExerciseAnalysers;
    }
    
    public void changeDestinationForOutput(HashMap<String, String> map)
    {
        if (map.containsKey(destination))
        {
            this.destinationForOutput = map.get(destination);
        }
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
                featuresToString += exercise.getAccelerometerData().featuresToString();
            }
            if (accelerometerRotated)
            {
                featuresToString += exercise.getAccelerometerRotatedData().featuresToString();
            }
            if (accelerometerNoGravity)
            {
                featuresToString += exercise.getAccelerometerNoGravityData().featuresToString();
            }
            if (accelerometerNoGravityRotated)
            {
                featuresToString += exercise.getAccelerometerNoGravityRotatedData().featuresToString();
            }
            if (linear)
            {
                featuresToString += exercise.getLinearData().featuresToString();
            }
            if (linearRotated)
            {
                featuresToString += exercise.getLinearRotatedData().featuresToString();
            }
            if (rotation)
            {
                featuresToString += exercise.getRotationData().featuresToString();
            }
            if (gravity)
            {
                featuresToString += exercise.getGravityData().featuresToString();
            }
            if (gyroscope)
            {
                featuresToString += exercise.getGyroscopeData().featuresToString();
            }
            if (magneticField)
            {
                featuresToString += exercise.getMagneticFieldData().featuresToString();
            }
            if (ambientTemperature)
            {
                featuresToString += exercise.getAmbientTemperatureData().featuresToString();
            }
            if (light)
            {
                featuresToString += exercise.getLightData().featuresToString();
            }
            if (pressure)
            {
                featuresToString += exercise.getPressureData().featuresToString();
            }
            if (relativeHumidity)
            {
                featuresToString += exercise.getRelativeHumidityData().featuresToString();
            }
            
            if (!featuresToString.equals(""))
            {
                finalString += featuresToString + destinationForOutput 
                        + System.getProperty("line.separator");
            }
        }
        return finalString;
    }
    
    public String getPrologueARFF(boolean accelerometer, boolean accelerometerRotated,
            boolean accelerometerNoGravity, boolean accelerometerNoGravityRotated, 
            boolean linear, boolean linearRotated, boolean rotation, boolean gravity, 
            boolean gyroscope, boolean magneticField, boolean ambientTemperature, 
            boolean light, boolean pressure, boolean relativeHumidity)
    {
        String result = "";
        if (accelerometer)
        {
            result += getAccelerometerPrologueARFF();
        }
        if (accelerometerRotated)
        {
            result += getAccelerometerRotatedPrologueARFF();
        }
        if (accelerometerNoGravity)
        {
            result += getAccelerometerNoGravityPrologueARFF();
        }
        if (accelerometerNoGravityRotated)
        {
            result += getAccelerometerNoGravityRotatedPrologueARFF();
        }
        if (linear)
        {
            result += getLinearPrologueARFF();
        }
        if (linearRotated)
        {
            result += getLinearRotatedPrologueARFF();
        }
        if (rotation)
        {
            result += getRotationPrologueARFF();
        }
        if (gravity)
        {
            result += getGravityPrologueARFF();
        }
        if (gyroscope)
        {
            result += getGyroscopePrologueARFF();
        }
        if (magneticField)
        {
            result += getMagneticFieldPrologueARFF();
        }
        if (ambientTemperature)
        {
            result += getAmbientTemperaturePrologueARFF();
        }
        if (light)
        {
            result += getLightPrologueARFF();
        }
        if (pressure)
        {
            result += getPressurePrologueARFF();
        }
        if (relativeHumidity)
        {
            result += getRelativeHumidityPrologueARFF();
        }
        return result;
    }
    
    private String getAccelerometerPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAccelerometerData().featuresNameToARFF();
    }
    
    private String getAccelerometerRotatedPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAccelerometerRotatedData().featuresNameToARFF();
    }
    
    private String getAccelerometerNoGravityPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAccelerometerNoGravityData().featuresNameToARFF();
    }
    
    private String getAccelerometerNoGravityRotatedPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAccelerometerNoGravityRotatedData().featuresNameToARFF();
    }
    
    private String getLinearPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getLinearData().featuresNameToARFF();
    }
    
    private String getLinearRotatedPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getLinearRotatedData().featuresNameToARFF();
    }
    
    private String getRotationPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getRotationData().featuresNameToARFF();
    }
    
    private String getGravityPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getGravityData().featuresNameToARFF();
    }
    
    private String getGyroscopePrologueARFF()
    {
        return listExerciseAnalysers.get(0).getGyroscopeData().featuresNameToARFF();
    }
    
    private String getMagneticFieldPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getGyroscopeData().featuresNameToARFF();
    }
    
    private String getAmbientTemperaturePrologueARFF()
    {
        return listExerciseAnalysers.get(0).getAmbientTemperatureData().featuresNameToARFF();
    }
    
    private String getLightPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getLightData().featuresNameToARFF();
    }
    
    private String getPressurePrologueARFF()
    {
        return listExerciseAnalysers.get(0).getPressureData().featuresNameToARFF();
    }
    
    private String getRelativeHumidityPrologueARFF()
    {
        return listExerciseAnalysers.get(0).getRelativeHumidityData().featuresNameToARFF();
    }
}
