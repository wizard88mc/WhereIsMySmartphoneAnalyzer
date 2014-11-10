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
    
    public DataExtractorOnlyDataBefore(ArrayList<Exercise> allExercises, String destination,
            long bufferDuration, int frequency)
    {
        mSetOfExercises = new SetOfExercisesForSpecificDestination(allExercises, 
                destination);
        
        for (Exercise exercise: mSetOfExercises.getExercises())
        {
            listExerciseAnalysesBefore.add(new ExerciseAnalyserBefore(bufferDuration, 
                    exercise.getReadingsAccelerometer(), 
                    exercise.getReadingsLinear(), frequency));
        }
    }
}
