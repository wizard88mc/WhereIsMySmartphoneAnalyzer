package exerciseanalyser.analyserafter;

import exerciseanalyser.DataExtractor;
import java.util.ArrayList;
import models.Exercise;

/**
 * This class holds the extracted data from readings only after the proximity 
 * sensor value becomes = 0
 * @author Matteo Ciman
 */
public class DataExtractorOnlyDataAfter extends DataExtractor
{
    
    public DataExtractorOnlyDataAfter(ArrayList<Exercise> allExercises, String destination,
            long bufferDuration, int frequency)
    {
        super(allExercises, destination);
        
        for (Exercise exercise: mSetOfExercises.getExercises())
        {
            listExerciseAnalysers.add(new ExerciseAnalyserAfter(bufferDuration, 
                    exercise.getReadingsAccelerometer(), 
                    exercise.getReadingsLinear(), frequency));
        }
    }
}
