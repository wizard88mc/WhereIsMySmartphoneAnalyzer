package features.threeaxesdata;

import features.DataSet;
import features.OneAxisData;
import java.util.ArrayList;

/**
 * Holds data for a sensor with three axes of data
 * @author Matteo Ciman
 * @version 0.1
 * @since 2014-11-08
 */
public class ThreeAxesData extends OneAxisData
{
    protected DataSet secondAxis = null;
    protected DataSet thirdAxis = null;
    
    public ThreeAxesData(String first, ArrayList<Float> xData, String second, 
            ArrayList<Float> yData, String third, ArrayList<Float> zData)
    {
        super(first, xData, null);
        secondAxis = new DataSet(second, yData);
        thirdAxis = new DataSet(third, zData);
    }
    
    @Override
    public void calculateFeatures()
    {
        addBasicFeatures(firstAxis, secondAxis, thirdAxis);
        
        features.addAll(calculateAverageRatios(firstAxis, secondAxis, thirdAxis));
        features.addAll(calculateStdRatios(firstAxis, secondAxis, thirdAxis));
        features.addAll(calculateVarianceRatios(firstAxis, secondAxis, thirdAxis));
        features.addAll(calculateMinRatios(firstAxis, secondAxis, thirdAxis));
        features.addAll(calculateMaxRatios(firstAxis, secondAxis, thirdAxis));
        features.addAll(calculateRatiosDifferenceMinMax(firstAxis, secondAxis, thirdAxis));
        calculateMagnitudeArea(firstAxis, secondAxis, thirdAxis, features);
        calculateSignalMagnitudeArea(firstAxis, secondAxis, thirdAxis, features);
        features.addAll(calculateCorrelation(firstAxis, secondAxis, thirdAxis));
    }
    
    /**
     * Calculates the ratios between all the averages of all the axes
     * @param params all the axes to calculate average ratios
     * @return ArrayList<Float> ratios
     */
    protected ArrayList<Float> calculateAverageRatios(DataSet... params)
    {
        ArrayList<Float> ratios = new ArrayList<Float>();
        for (int i = 0; i < params.length - 1; i++)
        {
            for (int j = i + 1; j < params.length; j++)
            {
                if (params[i].getAverage() != null && 
                        params[j].getAverage() != null)
                {
                    Float ratio = params[i].getAverage() / params[j].getAverage();
                    if (ratio.isInfinite() || ratio.isNaN())
                    {
                        ratio = 0.0F;
                    }
                    ratios.add(ratio);
                    featuresName.add("RATIO_AVERAGE_" + params[i].getName() + "_" 
                        + params[j].getName());
                }
            }
        }
        
        return ratios;
    }
    
    /**
     * calculates the ratios between the STD of each axis
     * @param params a list of axis
     * @return the ratios between each axis
     */
    protected ArrayList<Float> calculateStdRatios(DataSet... params)
    {
        ArrayList<Float> ratios = new ArrayList<Float>();
        
        for (int i = 0; i < params.length - 1; i++)
        {
            for (int j = i + 1; j < params.length; j++)
            {
                if (params[i].getStd() != null && 
                        params[j].getStd() != null)
                {
                    Float ratio = params[i].getStd() / params[j].getStd();
                    if (ratio.isInfinite() || ratio.isNaN())
                    {
                        ratio = 0.0F;
                    }
                    ratios.add(ratio);
                    featuresName.add("RATIO_STD_" + params[i].getName() + "_" 
                        + params[j].getName());
                }
            }
        }
        
        return ratios;
    }
    
    /**
     * Calculates the ratios between the variance of each axis
     * @param params a list of axes
     * @return all the ratios between the variance of each axes
     */
    protected ArrayList<Float> calculateVarianceRatios(DataSet... params)
    {
        ArrayList<Float> ratios = new ArrayList<Float>();
        
        for (int i = 0; i < params.length - 1; i++)
        {
            for (int j = i + 1; j < params.length; j++)
            {
                if (params[i].getVariance() != null && 
                        params[j].getVariance() != null)
                {
                    Float ratio = params[i].getVariance() / params[j].getVariance(); 
                    if (ratio.isInfinite() || ratio.isNaN())
                    {
                        ratio = 0.0F;
                    }
                    ratios.add(ratio);
                    featuresName.add("RATIO_VARIANCE_" + params[i].getName() + 
                        "_" + params[j].getName());
                }
            }
        }
        
        return ratios;
    }
    
    /**
     * Calculates the ratios between the difference between the min and max value
     * of each axis
     * @param params a list of axes
     * @return all the ratios between the difference between the min and max value
     */
    protected ArrayList<Float> calculateRatiosDifferenceMinMax(DataSet... params)
    {
        ArrayList<Float> ratios = new ArrayList<Float>();
        
        for (int i = 0; i < params.length - 1; i++)
        {
            for (int j = i + 1; j < params.length; j++)
            {
                if (params[i].getDifferenceMinMax() != null && 
                        params[j].getDifferenceMinMax() != null)
                {
                    Float ratio = params[i].getDifferenceMinMax() / 
                            params[j].getDifferenceMinMax();
                    if (ratio.isInfinite() || ratio.isNaN())
                    {
                        ratio = 0.0F;
                    }
                    ratios.add(ratio);
                    featuresName.add("RATIO_DIFF_MIN_MAX_" + params[i].getName() + 
                        "_" + params[j].getName());
                }
            }
        }
        
        return ratios;
    }
    
    /**
     * Calculates the ratios between the max value of each axis
     * @param params a list of axes
     * @return the ratios of the max values
     */
    protected ArrayList<Float> calculateMaxRatios(DataSet... params)
    {
        ArrayList<Float> ratios = new ArrayList<Float>();
        
        for (int i = 0; i < params.length - 1; i++)
        {
            for (int j = i + 1; j < params.length; j++)
            {
                if (params[i].getMax() != null && params[j].getMax() != null)
                {
                    Float ratio = params[i].getMax() / params[j].getMax();
                    if (ratio.isInfinite() || ratio.isNaN())
                    {
                        ratio = 0.0F;
                    }
                    ratios.add(ratio);
                    featuresName.add("RATIO_MAX_" + params[i].getName() + "_" + 
                        params[j].getName());
                }
            }
        }
        return ratios;
    }
    
    /**
     * Calculates the ratio between the min value of all the axes
     * @param params a list of axes
     * @return the ratio between the min values of each axis
     */
    protected ArrayList<Float> calculateMinRatios(DataSet... params)
    {
        ArrayList<Float> ratios = new ArrayList<Float>();
        
        for (int i = 0; i < params.length - 1; i++)
        {
            for (int j = i + 1; j < params.length; j++)
            {
                if (params[i].getMin() != null && params[j].getMax() != null)
                {
                    Float ratio = params[i].getMin() / params[j].getMin(); 
                    if (ratio.isInfinite() || ratio.isNaN())
                    {
                        ratio = 0.0F;
                    }
                    ratios.add(ratio);
                    featuresName.add("RATIO_MIN_" + params[i].getName() + "_" + 
                        params[j].getName());
                }
            }
        }
        return ratios;
    }
    
    /**
     * Calculates the magnitude area of the three axes (used only with motion 
     * data)
     * @param first the first axis
     * @param second the second axis
     * @param third the third axis
     * @param features ArrayList for the features
     */
    protected void calculateMagnitudeArea(DataSet first, DataSet second, 
            DataSet third, ArrayList<Float> features)
    {
        
        if (first.getAverage() != null && second.getAverage() != null && 
                third.getAverage() != null)
        {
            featuresName.add(first.getName() + "_" + second.getName() + "_" + 
                    third.getName() + "_" + "MAGNITUDE_AREA");
            features.add((Float) ((Double)((Math.sqrt(Math.pow(first.getAverage(), 2) + 
                Math.pow(second.getAverage(), 2) + 
                Math.pow(third.getAverage(), 2))) / first.getNumberData())).floatValue());
        }
    }
    
    /**
     * Calculate the signal magnitude area of the three axes (used only on 
     * motion data)
     * @param first the first axis
     * @param second the second axis
     * @param third the third axis
     * @return the signal magnitude area
     */
    protected void calculateSignalMagnitudeArea(DataSet first, DataSet second, 
            DataSet third, ArrayList<Float> features)
    {
        int counter = 0;
        float signalMagnitudeArea = 0;
        
        ArrayList<Float> firstDataSet = first.getDataSet(), 
                secondDataSet = second.getDataSet(), 
                thirdDataSet = third.getDataSet();
        
        for (int i = 0; i < firstDataSet.size(); i++)
        {
            if (i < secondDataSet.size() && i < thirdDataSet.size())
            {
                if (firstDataSet.get(i) != null && secondDataSet.get(i) != null
                        && thirdDataSet.get(i) != null)
                {
                    counter++;
                    signalMagnitudeArea += Math.abs(firstDataSet.get(i)) + 
                            Math.abs(secondDataSet.get(i)) + 
                            Math.abs(thirdDataSet.get(i));
                }
            }
        }
        
        if (counter != 0)
        {
            featuresName.add(first.getName() + "_" + second.getName() + "_" + 
                    third.getName() + "_" + "SIGNAL_MAGNITUDE_AREA");
            signalMagnitudeArea /= counter;
            features.add(signalMagnitudeArea);
        }
    }
    
    /**
     * Calculate the correlation between all axes
     * @param params the axes to calculate the correlation
     * @return all the correlations between the axes
     */
    protected ArrayList<Float> calculateCorrelation(DataSet... params)
    {
        ArrayList<Float> correlations = new ArrayList<Float>();
        
        for (int i = 0; i < params.length - 1; i++)
        {
            for (int j = i + 1; j < params.length; j++)
            {
                if (params[i].getStd() != null && params[j].getStd() != null)
                {
                    float covariance = calculateCovariance(params[i].getDataSet(), 
                            params[j].getDataSet());
                    Float correlation = covariance / (params[i].getStd() * params[j].getStd());
                    if (correlation.isInfinite() || correlation.isNaN())
                    {
                        correlation = 0.0F;
                    }
                    correlations.add(correlation);
                    featuresName.add("CORRELATION_" + params[i].getName() + "_" + 
                        params[j].getName());
                }
            }
        }
        return correlations;
    }
    
    /**
     * Calculate the covariance between two axes
     * @param first the first axis
     * @param second the second axis
     * @return the covariance
     */
    private float calculateCovariance(ArrayList<Float> first, ArrayList<Float> second)
    {
        int counter = 0;
        float productFirstSecond = 0, sumX = 0, sumY = 0;
        for (int i = 0; i < first.size(); i++)
        {
            if (i < second.size() && first.get(i) != null && second.get(i) != null)
            {
                counter++;
                productFirstSecond += (first.get(i) * second.get(i));
                sumX += first.get(i);
                sumY += second.get(i);
            }
        }
        
        return (productFirstSecond / counter) - (sumX * sumY) / (float)Math.pow(counter, 2);
    }
}
