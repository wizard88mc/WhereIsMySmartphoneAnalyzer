package whereismysmartphoneanalyzer;

import java.util.ArrayList;
import models.Exercise;

/**
 * This class holds all the exercises with a specific destination label
 * @author Matteo Ciman
 * @since 2014-11-08
 * @version 0.1
 */
public class SetOfExercisesForSpecificDestination 
{
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private final String targetDestination;
    
    public SetOfExercisesForSpecificDestination(ArrayList<Exercise> allExercises, 
            String targetDestination)
    {
        this.targetDestination = targetDestination;
        
        addCorrectExercisesToList(allExercises);
    }
    
    /**
     * Adds the exercises with the specific target destination to the list
     * of all exercises
     * @param allExercises all the exercises extracted
     */
    private void addCorrectExercisesToList(ArrayList<Exercise> allExercises)
    {
        for (Exercise exercise: allExercises)
        {
            if (exercise.getDestination().equals(targetDestination))
            {
                exercises.add(exercise);
            }
        }
    }
    
    public ArrayList<Exercise> getExercises()
    {
        return this.exercises;
    }
}
