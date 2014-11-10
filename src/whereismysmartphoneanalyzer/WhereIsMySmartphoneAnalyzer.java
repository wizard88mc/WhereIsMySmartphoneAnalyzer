/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package whereismysmartphoneanalyzer;

import filereader.AccelerometerReader;
import filereader.LinearReader;
import filereader.ListFilesReader;
import filereader.SettingsReader;
import java.util.ArrayList;
import models.Exercise;
import models.Reading;

/**
 * 
 * @author Matteo Ciman
 * @version 0.1
 * @since 2014-10-20
 */
public class WhereIsMySmartphoneAnalyzer 
{
    private static Integer[] bufferDurations = new Integer[]{500, 1000, 1500, 2000}; // in milliseconds
    private static String[] listDestinations = new String[]{
        ExercisesWorker.TASCA_DESTRA_DAVANTI_PANTALONI, ExercisesWorker.TASCA_DESTRA_DIETRO_PANTALONI,
        ExercisesWorker.TASCA_SINISTRA_DAVANTI_PANTALONI, ExercisesWorker.TASCA_SINISTRA_DIETRO_PANTALONI,
        ExercisesWorker.TASCA_GIACCA_ALTA, ExercisesWorker.TASCA_GIACCA_BASSA, 
        ExercisesWorker.BORSA, ExercisesWorker.MARSUPIO, ExercisesWorker.ZAINO};
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {  
        ArrayList<Exercise> allExercises = new ArrayList<>();
        
        ListFilesReader mListFilesReader = new ListFilesReader();
        
        // Retrieving all the input files available
        ArrayList<String> inputFiles = mListFilesReader.readListFiles();
        
        for (String coupleInput: inputFiles)
        {
            String[] elements = coupleInput.split(",");
            try {
            SettingsReader mSettingsReader = new SettingsReader(elements[0], elements[1]);
            
            ArrayList<Exercise> exercisesForInput = mSettingsReader.retrieveTrunks();
            
            /**
             * Using the IMEI and the timestamp, we retrieve all the data for the
             * accelerometer and linear samples
             */
            AccelerometerReader mAccelerometerReader = 
                    new AccelerometerReader(elements[0], elements[1]);
            LinearReader mLinearReader = new LinearReader(elements[0], 
                    elements[1]);
            
            ArrayList<Reading> readingsAccelerometer = 
                    mAccelerometerReader.getListReadings(),
                    readingsLinear = mLinearReader.getListReadings();
            
            DataWorker.addReadingsToAllExercises(exercisesForInput, 
                    readingsAccelerometer, readingsLinear);
            
            allExercises.addAll(exercisesForInput);
            }
            catch(Exception exc)
            {
                System.out.println(exc.toString());
                exc.printStackTrace();
                System.out.println(elements[0] + "," + elements[1]);
            }
        }
        
        ExercisesWorker mExercisesWorker =  new ExercisesWorker(allExercises);
        
        mExercisesWorker.cleanExercises();
        
        mExercisesWorker.countNumberExercisesPerLabel();
        
        for (String target: listDestinations)
        {
            DataExtractorOnlyDataBefore mDataExtractorOnlyDataBefore = 
                    new DataExtractorOnlyDataBefore(allExercises, target, 
                            bufferDurations[0], 30);
        }
    }
}
