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
    
    /**
     * Returns a row for each exercise with the features for the example
     * @return list of features
     */
    public String accelerometerDataForAllExercises()
    {
        String finalString = "";
        for (ExerciseAnalyserBefore exercise: listExerciseAnalysesBefore)
        {
            finalString += exercise.getAccelerometerData().featuresToString() 
                    + destination + System.getProperty("line.separator");
        }
        return finalString;
    }
    
    public String getAccelerometerPrologueARFF()
    {
        return listExerciseAnalysesBefore.get(0).getAccelerometerData().featuresNameToARFF();
    }
}
