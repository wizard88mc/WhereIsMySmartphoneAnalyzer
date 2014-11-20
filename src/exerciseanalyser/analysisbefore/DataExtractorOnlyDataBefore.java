/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciseanalyser.analysisbefore;

import exerciseanalyser.DataExtractor;
import java.util.ArrayList;
import models.Exercise;

/**
 *
 * @author Matteo Ciman
 */
public class DataExtractorOnlyDataBefore extends DataExtractor
{   
    public DataExtractorOnlyDataBefore(ArrayList<Exercise> allExercises, 
            String activity, String destination, long bufferDuration, 
            int frequency)
    {   
        super(allExercises, activity, destination);
        
        for (Exercise exercise: mSetOfExercises.getExercises())
        {
            listExerciseAnalysers.add(new ExerciseAnalyserBefore(bufferDuration, 
                    exercise.getReadingsAccelerometer(), 
                    exercise.getReadingsLinear(), frequency));
        }
    }
}
