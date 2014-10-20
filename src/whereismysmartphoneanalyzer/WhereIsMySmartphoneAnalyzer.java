/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package whereismysmartphoneanalyzer;

import filereader.AccelerometerReader;
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
public class WhereIsMySmartphoneAnalyzer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        ListFilesReader mListFilesReader = new ListFilesReader();
        
        // Retrieving all the input files available
        ArrayList<String> inputFiles = mListFilesReader.readListFiles();
        
        for (String coupleInput: inputFiles)
        {
            String[] elements = coupleInput.split(",");
            SettingsReader mSettingsReader = new SettingsReader(elements[0], elements[1]);
            
            ArrayList<Exercise> exercisesForInput = mSettingsReader.retrieveTrunks();
            
            /**
             * Using the IMEI and the timestamp, we retrieve all the data for the
             * accelerometer and linear samples
             */
            AccelerometerReader mAccelerometerReader = new AccelerometerReader(elements[0], elements[1]);
            ArrayList<Reading> readings = mAccelerometerReader.getListReadings();
            
            DataWorker.addReadingsToAllExercises(exercisesForInput, readings);
        }
    }
    
}
