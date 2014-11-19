package whereismysmartphoneanalyzer;

import exerciseanalyser.analysisbefore.DataExtractorOnlyDataBefore;
import arff.ARFFFileCreator;
import exerciseanalyser.DataExtractor;
import exerciseanalyser.analyserafter.DataExtractorOnlyDataAfter;
import exerciseanalyser.analyserbeforeafter.DataExtractorBeforeAfter;
import exerciseanalyser.analyserbeforeafter.ExerciseAnalyserBeforeAfter;
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
    private static Integer[] frequencies = new Integer[]{15, 30, 50};
    public static String[] listDestinations = new String[]{
        ExercisesWorker.TASCA_DESTRA_DAVANTI_PANTALONI, ExercisesWorker.TASCA_DESTRA_DIETRO_PANTALONI,
        ExercisesWorker.TASCA_SINISTRA_DAVANTI_PANTALONI, ExercisesWorker.TASCA_SINISTRA_DIETRO_PANTALONI,
        ExercisesWorker.TASCA_GIACCA_ALTA, ExercisesWorker.TASCA_GIACCA_BASSA, 
        ExercisesWorker.BORSA, ExercisesWorker.MARSUPIO, ExercisesWorker.ZAINO};
    
    public static ArrayList<Sensor> listSensors = new ArrayList<>();
    
    static
    {
        listSensors.add(new Sensor("Accelerometer"));
        listSensors.add(new Sensor("AccelerometerRotated"));
        listSensors.add(new Sensor("AccelerometerNoGravity"));
        listSensors.add(new Sensor("AccelerometerNoGravityRotated"));
        listSensors.add(new Sensor("Linear"));
        listSensors.add(new Sensor("LinearRotated"));
        listSensors.add(new Sensor("Rotation"));
        listSensors.add(new Sensor("Gravity"));
        listSensors.add(new Sensor("Gyroscope"));
        listSensors.add(new Sensor("MagneticField"));
        listSensors.add(new Sensor("AmbientTemperature"));
        listSensors.add(new Sensor("Light"));
        listSensors.add(new Sensor("Pressure"));
        listSensors.add(new Sensor("RelativeHumidity"));
    }
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
                ArrayList<DataExtractor> listDataExtractorOnlyDataBefore = 
                        new ArrayList<>(), 
                        listDataExtractorOnlyDataAfter = new ArrayList<>();
                ArrayList<DataExtractorBeforeAfter> featuresBeforeAfter = new ArrayList<>();
                
                for (String target: listDestinations)
                {
                    DataExtractorOnlyDataBefore before = new DataExtractorOnlyDataBefore(allExercises, target, 
                                    bufferLenght, frequency);
                    DataExtractorOnlyDataAfter after = new DataExtractorOnlyDataAfter(allExercises, target, 
                                    bufferLenght, frequency);
                    listDataExtractorOnlyDataBefore.add(before);
                    listDataExtractorOnlyDataAfter.add(after);
                    
                    featuresBeforeAfter.add(new DataExtractorBeforeAfter(target, 
                            before.getListExerciseAnalyser(), after.getListExerciseAnalyser()));
                }
                ARFFFileCreator.createARFFDataOnlyBefore(listDataExtractorOnlyDataBefore, 
                        bufferLenght, frequency);
                ARFFFileCreator.createARFFDataOnlyAfter(listDataExtractorOnlyDataAfter, 
                        bufferLenght, frequency);
                ARFFFileCreator.createARFFDataBeforeAfter(featuresBeforeAfter, 
                        bufferLenght, frequency);
            }
        }
    }
}
