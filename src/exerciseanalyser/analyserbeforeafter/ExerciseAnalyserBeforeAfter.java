/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciseanalyser.analyserbeforeafter;

import exerciseanalyser.ExerciseAnalyser;
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
            
            featuresRatiosAccelerometerData.add("RATIO_" + featuresName.get(i));
            Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
            if (ratio.isInfinite() || ratio.isNaN())
            {
                ratio = 0.0F;
            }
            ratiosAccelerometerData.add(ratio);
            
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
            featuresRatiosAccelerometerRotatedData.add("RATIO_" + featuresName.get(i));
            Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
            if (ratio.isInfinite() || ratio.isNaN())
            {
                ratio = 0.0F;
            }
            ratiosAccelerometerRotatedData.add(ratio);
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
            featuresRatiosAccelerometerNoGravityData.add("RATIO_" + featuresName.get(i));
            Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
            if (ratio.isInfinite() || ratio.isNaN())
            {
                ratio = 0.0F;
            }
            ratiosAccelerometerNoGravityData.add(ratio);
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
            featuresRatiosAccelerometerNoGravityRotatedData.add("RATIO_" + featuresName.get(i));
            Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
            if (ratio.isInfinite() || ratio.isNaN())
            {
                ratio = 0.0F;
            }
            ratiosAccelerometerNoGravityRotatedData.add(ratio);
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
            featuresRatiosLinearData.add("RATIO_" + featuresName.get(i));
            Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
            if (ratio.isInfinite() || ratio.isNaN())
            {
                ratio = 0.0F;
            }
            ratiosLinearData.add(ratio);
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
            featuresRatiosLinearRotatedData.add("RATIO_" + featuresName.get(i));
            Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
            if (ratio.isInfinite() || ratio.isNaN())
            {
                ratio = 0F;
            }
            ratiosLinearRotatedData.add(ratio);
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
                Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
                if (ratio.isInfinite() || ratio.isNaN())
                {
                    ratio = 0.0F;
                }
                ratiosRotationData.add(ratio);
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
                Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
                if (ratio.isInfinite() || ratio.isNaN())
                {
                    ratio = 0F;
                }
                ratiosGravityData.add(ratio);
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
                Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
                if (ratio.isInfinite() || ratio.isNaN())
                {
                    ratio = 0F;
                }
                ratiosGyroscopeData.add(ratio);
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
                Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
                if (ratio.isInfinite() || ratio.isNaN())
                {
                    ratio = 0.0F;
                }
                ratiosMagneticFieldData.add(ratio);
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
                Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
                if (ratio.isInfinite() || ratio.isNaN())
                {
                    ratio = 0.0F;
                }
                ratiosAmbientTemperatureData.add(ratio);
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
                Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
                if (ratio.isInfinite() || ratio.isNaN())
                {
                    ratio = 0.0F;
                }
                ratiosLightData.add(ratio);
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
                Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
                if (ratio.isInfinite() || ratio.isNaN())
                {
                    ratio = 0.0F;
                }
                ratiosPressureData.add(ratio);
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
                Float ratio = featuresBefore.get(i) / featuresAfter.get(i);
                if (ratio.isInfinite() || ratio.isNaN())
                {
                    ratio = 0.0F;
                }
                ratiosRelativeHumidityData.add(ratio);
            }
        }
    }
    
    public String buildFeaturesListForARFF(boolean accelerometer, 
            boolean accelerometerRotated, boolean accelerometerNoGravity, 
            boolean accelerometerNoGravityRotated, boolean linear, 
            boolean linearRotated, boolean rotation, boolean gravity, 
            boolean gyroscope, boolean magneticField, boolean ambientTemperature, 
            boolean light, boolean pressure, boolean relativeHumidity)
    {
        ArrayList<String> listFeatures = new ArrayList<>();
        
        if (accelerometer)
        {
            listFeatures.addAll(getAccelerometerFeaturesName());
        }
        if (accelerometerRotated)
        {
            listFeatures.addAll(getAccelerometerRotatedFeaturesName());
        }
        if (accelerometerNoGravity)
        {
            listFeatures.addAll(getAccelerometerNoGravityFeaturesName());
        }
        if (accelerometerNoGravityRotated)
        {
            listFeatures.addAll(getAccelerometerNoGravityRotatedFeaturesName());
        }
        if (linear)
        {
            listFeatures.addAll(getLinearFeaturesName());
        }
        if (linearRotated)
        {
            listFeatures.addAll(getLinearRotatedFeaturesName());
        }
        if (rotation)
        {
            listFeatures.addAll(getRotationFeaturesName());
        }
        if (gravity)
        {
            listFeatures.addAll(getGravityFeaturesName());
        }
        if (gyroscope)
        {
            listFeatures.addAll(getGyroscopeFeaturesName());
        }
        if (magneticField)
        {
            listFeatures.addAll(getMagneticFieldFeaturesName());
        }
        if (ambientTemperature)
        {
            listFeatures.addAll(getAmbientTemperatureFeaturesName());
        }
        if (light)
        {
            listFeatures.addAll(getLightFeaturesName());
        }
        if (pressure)
        {
            listFeatures.addAll(getPressureFeaturesName());
        }
        if (relativeHumidity)
        {
            listFeatures.addAll(getRelativeHumidityFeaturesName());
        }
        
        String output = "";
        for (String feature: listFeatures)
        {
            output += "@ATTRIBUTE " + feature + " NUMERIC" 
                    + System.getProperty("line.separator");
        }
        return output;
    }
    
    public ArrayList<String> getAccelerometerFeaturesName()
    {
        return this.featuresRatiosAccelerometerData;
    }
    
    public ArrayList<Float> getAccelerometerFeaturesValues()
    {
        return this.ratiosAccelerometerData;
    }
    
    public ArrayList<String> getAccelerometerRotatedFeaturesName()
    {
        return this.featuresRatiosAccelerometerRotatedData;
    }
    
    public ArrayList<Float> getAccelerometerRotatedValues()
    {
        return this.ratiosAccelerometerRotatedData;
    }
    
    public ArrayList<String> getAccelerometerNoGravityFeaturesName()
    {
        return this.featuresRatiosAccelerometerNoGravityData;
    }
    
    public ArrayList<Float> getAccelerometerNoGravityValues()
    {
        return this.ratiosAccelerometerNoGravityData;
    }
    
    public ArrayList<String> getAccelerometerNoGravityRotatedFeaturesName()
    {
        return this.featuresRatiosAccelerometerNoGravityRotatedData;
    }
    
    public ArrayList<Float> getAccelerometerNoGravityRotatedValues()
    {
        return this.ratiosAccelerometerNoGravityRotatedData;
    }
    
    public ArrayList<String> getLinearFeaturesName()
    {
        return this.featuresRatiosLinearData;
    }
    
    public ArrayList<Float> getLinearValues()
    {
        return this.ratiosLinearData;
    }
    
    public ArrayList<String> getLinearRotatedFeaturesName()
    {
        return this.featuresRatiosLinearRotatedData;
    }
    
    public ArrayList<Float> getLinearRotatedValues()
    {
        return this.ratiosLinearRotatedData;
    }
    
    public ArrayList<String> getRotationFeaturesName()
    {
        return this.featuresRatiosRotationData;
    }
    
    public ArrayList<Float> getRotationValues()
    {
        return this.ratiosRotationData;
    }
    
    public ArrayList<String> getGravityFeaturesName()
    {
        return this.featuresRatiosGravityData;
    }
    
    public ArrayList<Float> getGravityValues()
    {
        return this.ratiosGravityData;
    }
    
    public ArrayList<String> getGyroscopeFeaturesName()
    {
        return this.featuresRatiosGyroscopeData;
    }
    
    public ArrayList<Float> getGyroscopeValues()
    {
        return this.ratiosGyroscopeData;
    }
    
    public ArrayList<String> getMagneticFieldFeaturesName()
    {
        return this.featuresRatiosMagneticFieldData;
    }
    
    public ArrayList<Float> getMagneticFieldValues()
    {
        return this.ratiosMagneticFieldData;
    }
    
    public ArrayList<String> getAmbientTemperatureFeaturesName()
    {
        return this.featuresRatiosAmbientTemperatureData;
    }
    
    public ArrayList<Float> getAmbientTemperatureValues()
    {
        return this.ratiosAmbientTemperatureData;
    }
    
    public ArrayList<String> getLightFeaturesName()
    {
        return this.featuresRatiosLightData;
    }
    
    public ArrayList<Float> getLightValues()
    {
        return this.ratiosLightData;
    }
    
    public ArrayList<String> getPressureFeaturesName()
    {
        return this.featuresRatiosPressureData;
    }
    
    public ArrayList<Float> getPressureValues()
    {
        return this.ratiosPressureData;
    }
    
    public ArrayList<String> getRelativeHumidityFeaturesName()
    {
        return this.featuresRatiosRelativeHumidity;
    }
    
    public ArrayList<Float> getRelativeHumidityValues()
    {
        return this.ratiosRelativeHumidityData;
    }
}
