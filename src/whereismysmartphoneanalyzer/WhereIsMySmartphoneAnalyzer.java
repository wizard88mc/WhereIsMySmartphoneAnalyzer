package whereismysmartphoneanalyzer;

import arff.ARFFFileCreator;
import filereader.AccelerometerReader;
import filereader.FileReader;
import filereader.LinearReader;
import filereader.ListFilesReader;
import filereader.SettingsReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    private static Integer[] frequencies = new Integer[]{15, 30, 50};
    public static String[] listDestinations = new String[]{
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
        
        System.out.println("Esercizi partenza: " + allExercises.size());
        mExercisesWorker.cleanExercises();
        
        mExercisesWorker.countNumberExercisesPerLabel();
        
        for (int bufferLenght: bufferDurations)
        {
            for (int frequency: frequencies)
            {
                ArrayList<DataExtractorOnlyDataBefore> listDataExtractorOnlyDataBefore = 
                        new ArrayList<>();
                
                for (String target: listDestinations)
                {
                    listDataExtractorOnlyDataBefore.add( 
                            new DataExtractorOnlyDataBefore(allExercises, target, 
                                    bufferLenght, frequency));
                }
                
                ARFFFileCreator.createARFFAccelerometerDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFLinearDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFRotationDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFGravityDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFGyroscopeDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFMagneticFieldDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFAmbientTemperatureDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFLightDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFPressureDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
                ARFFFileCreator.createARFFRelativeHumidityDataOnlyBefore(listDataExtractorOnlyDataBefore, bufferLenght, frequency);
            }
        }
    }
}
