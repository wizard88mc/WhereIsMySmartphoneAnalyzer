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
public class DataWorker 
{

    public static void addReadingsToExercise(Exercise exercise, 
            ArrayList<Reading> readings)
    {
        
    }
    
    public static void addReadingsToAllExercises(ArrayList<Exercise> exercises,
            ArrayList<Reading> readings)
    {
        for (Exercise exercise: exercises)
        {
            DataWorker.addReadingsToExercise(exercise, readings);
        }
    }
}
