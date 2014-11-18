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
public class DataExtractorBeforeAfter
{
    private String target;
    private ArrayList<ExerciseAnalyserBeforeAfter> listExerciseAnalyserBeforeAfter = new ArrayList<>();
    
    public DataExtractorBeforeAfter(String target, ArrayList<ExerciseAnalyser> before, 
            ArrayList<ExerciseAnalyser> after)
    {
        this.target = target;
        for (int i = 0; i < before.size(); i++)
        {
            listExerciseAnalyserBeforeAfter.add(new ExerciseAnalyserBeforeAfter(before.get(i), after.get(i)));
        }
    }
}
