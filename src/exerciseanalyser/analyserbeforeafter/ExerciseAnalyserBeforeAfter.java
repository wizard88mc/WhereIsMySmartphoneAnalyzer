/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciseanalyser.analyserbeforeafter;

import exerciseanalyser.ExerciseAnalyser;
import exerciseanalyser.analyserafter.ExerciseAnalyserAfter;
import exerciseanalyser.analysisbefore.ExerciseAnalyserBefore;
import java.util.ArrayList;

/**
 *
 * @author matteo
 */
public class ExerciseAnalyserBeforeAfter 
{
    private final ArrayList<Float> ratiosAccelerometerData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosAccelerometerData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosAccelerometerRotatedData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosAccelerometerRotatedData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosAccelerometerNoGravityData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosAccelerometerNoGravityData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosAccelerometerNoGravityRotatedData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosAccelerometerNoGravityRotatedData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosLinearData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosLinearData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosLinearRotatedData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosLinearRotatedData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosRotationData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosRotationData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosGravityData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosGravityData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosGyroscopeData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosGyroscopeData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosMagneticFieldData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosMagneticFieldData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosAmbientTemperatureData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosAmbientTemperatureData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosLightData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosLightData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosPressureData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosPressureData = new ArrayList<>();
    
    private final ArrayList<Float> ratiosRelativeHumidityData = new ArrayList<>();
    private final ArrayList<String> featuresRatiosRelativeHumidity = new ArrayList<>();
    
    public ExerciseAnalyserBeforeAfter(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        calculateAccelerometerRatios(before, after);
        calculateAccelerometerRotatedRatios(before, after);
        calculateAccelerometerNoGravityRatios(before, after);
        calculateAccelerometerNoGravityRotatedRatios(before, after);
        calculateLinearRatios(before, after);
        calculateLinearRotatedRatios(before, after);
        calculateRotationRatios(before, after);
        calculateGravityRatios(before, after);
        calculateGyroscopeRatios(before, after);
        calculateMagneticFieldRatios(before, after);
        calculateAmbientTemperatureRatios(before, after);
        calculateLightRatios(before, after);
        calculatePressureRatios(before, after);
        calculateRelativeHumidityRatios(before, after);
    }
    
    private void calculateAccelerometerRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getAccelerometerData().getFeatures(),
                featuresAfter = after.getAccelerometerData().getFeatures();
        
        ArrayList<String> featuresName = before.getAccelerometerData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosAccelerometerData.add("RATIO_" + featuresName.get(i));
                ratiosAccelerometerData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateAccelerometerRotatedRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getAccelerometerRotatedData().getFeatures(),
                featuresAfter = after.getAccelerometerRotatedData().getFeatures();
        ArrayList<String> featuresName = before.getAccelerometerRotatedData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosAccelerometerRotatedData.add("RATIO_" + featuresName.get(i));
                ratiosAccelerometerRotatedData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateAccelerometerNoGravityRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getAccelerometerNoGravityData().getFeatures(),
                featuresAfter = after.getAccelerometerNoGravityData().getFeatures();
        ArrayList<String> featuresName = before.getAccelerometerNoGravityData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosAccelerometerNoGravityData.add("RATIO_" + featuresName.get(i));
                ratiosAccelerometerNoGravityData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateAccelerometerNoGravityRotatedRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getAccelerometerNoGravityRotatedData().getFeatures(),
                featuresAfter = after.getAccelerometerNoGravityRotatedData().getFeatures();
        ArrayList<String> featuresName = before.getAccelerometerNoGravityRotatedData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosAccelerometerNoGravityRotatedData.add("RATIO_" + featuresName.get(i));
                ratiosAccelerometerNoGravityRotatedData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateLinearRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getLinearData().getFeatures(),
                featuresAfter = after.getLinearData().getFeatures();
        ArrayList<String> featuresName = before.getLinearData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosLinearData.add("RATIO_" + featuresName.get(i));
                ratiosLinearData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateLinearRotatedRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getLinearRotatedData().getFeatures(),
                featuresAfter = after.getLinearRotatedData().getFeatures();
        ArrayList<String> featuresName = before.getLinearRotatedData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosLinearRotatedData.add("RATIO_" + featuresName.get(i));
                ratiosLinearRotatedData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateRotationRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getRotationData().getFeatures(),
                featuresAfter = after.getRotationData().getFeatures();
        ArrayList<String> featuresName = before.getRotationData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosRotationData.add("RATIO_" + featuresName.get(i));
                ratiosRotationData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateGravityRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getGravityData().getFeatures(),
                featuresAfter = after.getGravityData().getFeatures();
        ArrayList<String> featuresName = before.getGravityData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosGravityData.add("RATIO_" + featuresName.get(i));
                ratiosGravityData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateGyroscopeRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getGyroscopeData().getFeatures(),
                featuresAfter = after.getGyroscopeData().getFeatures();
        ArrayList<String> featuresName = before.getGyroscopeData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosGyroscopeData.add("RATIO_" + featuresName.get(i));
                ratiosGyroscopeData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateMagneticFieldRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getMagneticFieldData().getFeatures(),
                featuresAfter = after.getMagneticFieldData().getFeatures();
        ArrayList<String> featuresName = before.getMagneticFieldData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosMagneticFieldData.add("RATIO_" + featuresName.get(i));
                ratiosMagneticFieldData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateAmbientTemperatureRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getAmbientTemperatureData().getFeatures(),
                featuresAfter = after.getAmbientTemperatureData().getFeatures();
        ArrayList<String> featuresName = before.getAmbientTemperatureData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosAmbientTemperatureData.add("RATIO_" + featuresName.get(i));
                ratiosAmbientTemperatureData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateLightRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getLightData().getFeatures(),
                featuresAfter = after.getLightData().getFeatures();
        ArrayList<String> featuresName = before.getLightData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosLightData.add("RATIO_" + featuresName.get(i));
                ratiosLightData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculatePressureRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getPressureData().getFeatures(),
                featuresAfter = after.getPressureData().getFeatures();
        ArrayList<String> featuresName = before.getPressureData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosPressureData.add("RATIO_" + featuresName.get(i));
                ratiosPressureData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
    
    private void calculateRelativeHumidityRatios(ExerciseAnalyser before, 
            ExerciseAnalyser after)
    {
        ArrayList<Float> featuresBefore = before.getRelativeHumidityData().getFeatures(),
                featuresAfter = after.getRelativeHumidityData().getFeatures();
        ArrayList<String> featuresName = before.getRelativeHumidityData().getFeaturesName();
        
        for (int i = 0; i < featuresBefore.size(); i++)
        {
            if (featuresBefore.get(i) != null && !featuresBefore.get(i).isInfinite() && 
                    !featuresBefore.get(i).isNaN() && featuresAfter.get(i) != null && 
                    !featuresAfter.get(i).isInfinite() && !featuresAfter.get(i).isNaN())
            {
                featuresRatiosRelativeHumidity.add("RATIO_" + featuresName.get(i));
                ratiosRelativeHumidityData.add(featuresBefore.get(i) / featuresAfter.get(i));
            }
        }
    }
}
