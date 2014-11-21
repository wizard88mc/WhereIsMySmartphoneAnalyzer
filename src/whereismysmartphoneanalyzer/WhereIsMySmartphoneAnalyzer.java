package whereismysmartphoneanalyzer;

import exerciseanalyser.analysisbefore.DataExtractorOnlyDataBefore;
import arff.ARFFFileCreator;
import exerciseanalyser.DataExtractor;
import exerciseanalyser.analyserafter.DataExtractorOnlyDataAfter;
import exerciseanalyser.analyserbeforeafter.DataExtractorBeforeAfter;
import filereader.AccelerometerReader;
import filereader.FileReader;
import filereader.LinearReader;
import filereader.ListFilesReader;
import filereader.SettingsReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import models.Exercise;
import models.Reading;
import weka.Evaluation;

/**
 * 
 * @author Matteo Ciman
 * @version 0.1
 * @since 2014-10-20
 */
public class WhereIsMySmartphoneAnalyzer 
{
    private static final Integer[] bufferDurations = new Integer[]{500, 1000, 1500, 2000}; // in milliseconds
    private static final Integer[] frequencies = new Integer[]{15, 30, 50};
    private static final String[] activities = new String[] {"FERMO", "SEDUTO", 
        "CAMMINANDO", "SCALE_SU", "SCALE_GIU", null};
    public static String[] listDestinations = new String[]{
        ExercisesWorker.TASCA_DESTRA_DAVANTI_PANTALONI, ExercisesWorker.TASCA_DESTRA_DIETRO_PANTALONI,
        ExercisesWorker.TASCA_SINISTRA_DAVANTI_PANTALONI, ExercisesWorker.TASCA_SINISTRA_DIETRO_PANTALONI,
        ExercisesWorker.TASCA_GIACCA_ALTA, ExercisesWorker.TASCA_GIACCA_BASSA, 
        ExercisesWorker.BORSA, ExercisesWorker.MARSUPIO, ExercisesWorker.ZAINO};
    
    public static ArrayList<HashMap<String, String>> simplifiedMaps = new ArrayList<>();
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
    
    static {
        HashMap<String, String> firstMap = new HashMap<>(), 
                secondMap = new HashMap<>(), thirdMap = new HashMap<>(), 
                fourthMap = new HashMap<>();
        firstMap.put(ExercisesWorker.TASCA_DESTRA_DAVANTI_PANTALONI, "DAVANTI_PANTALONI");
        firstMap.put(ExercisesWorker.TASCA_DESTRA_DIETRO_PANTALONI, "DIETRO_PANTALONI");
        firstMap.put(ExercisesWorker.TASCA_SINISTRA_DAVANTI_PANTALONI, "DAVANTI_PANTALONI");
        firstMap.put(ExercisesWorker.TASCA_SINISTRA_DIETRO_PANTALONI, "DIETRO_PANTALONI");
        simplifiedMaps.add(firstMap);
        
        secondMap.put(ExercisesWorker.TASCA_DESTRA_DAVANTI_PANTALONI, "DESTRA_PANTALONI");
        secondMap.put(ExercisesWorker.TASCA_DESTRA_DIETRO_PANTALONI, "DESTRA_PANTALONI");
        secondMap.put(ExercisesWorker.TASCA_SINISTRA_DAVANTI_PANTALONI, "SINISTRA_PANTALONI");
        secondMap.put(ExercisesWorker.TASCA_SINISTRA_DIETRO_PANTALONI, "SINISTRA_PANTALONI");
        simplifiedMaps.add(secondMap);
        
        thirdMap.put(ExercisesWorker.TASCA_GIACCA_ALTA, "TASCA_GIACCA");
        thirdMap.put(ExercisesWorker.TASCA_GIACCA_BASSA, "TASCA_GIACCA");
        simplifiedMaps.add(thirdMap);
        
        fourthMap.put(ExercisesWorker.TASCA_DESTRA_DAVANTI_PANTALONI, "PANTALONI");
        fourthMap.put(ExercisesWorker.TASCA_DESTRA_DIETRO_PANTALONI, "PANTALONI");
        fourthMap.put(ExercisesWorker.TASCA_SINISTRA_DAVANTI_PANTALONI, "PANTALONI");
        fourthMap.put(ExercisesWorker.TASCA_SINISTRA_DIETRO_PANTALONI, "PANTALONI");
        fourthMap.put(ExercisesWorker.TASCA_GIACCA_ALTA, "GIACCA");
        fourthMap.put(ExercisesWorker.TASCA_GIACCA_BASSA, "GIACCA");
        simplifiedMaps.add(fourthMap);
    }
    
    public static final ArrayList<String> generatedFiles = new ArrayList<>();
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
        
        for (String activity: activities)
        {
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
                        DataExtractorOnlyDataBefore before = 
                                new DataExtractorOnlyDataBefore(allExercises, 
                                        activity, target, bufferLenght, 
                                        frequency);
                        DataExtractorOnlyDataAfter after = 
                                new DataExtractorOnlyDataAfter(allExercises, 
                                        activity, target, bufferLenght, frequency);
                        
                        listDataExtractorOnlyDataBefore.add(before);
                        listDataExtractorOnlyDataAfter.add(after);

                        featuresBeforeAfter.add(new DataExtractorBeforeAfter(target, 
                                activity, before.getListExerciseAnalyser(), 
                                after.getListExerciseAnalyser()));
                    }

                    ARFFFileCreator.createARFFData(listDataExtractorOnlyDataBefore, 
                            listDataExtractorOnlyDataAfter, featuresBeforeAfter, 
                            activity, bufferLenght, frequency, null);
                    
                    int counter = 1;
                    for (HashMap<String, String> map: simplifiedMaps)
                    {
                        for (DataExtractor extractor: listDataExtractorOnlyDataBefore)
                        {
                            extractor.changeDestinationForOutput(map);
                        }
                        for (DataExtractor extractor: listDataExtractorOnlyDataAfter)
                        {
                            extractor.changeDestinationForOutput(map);
                        }
                        for (DataExtractorBeforeAfter extractor: featuresBeforeAfter)
                        {
                            extractor.changeDestinationForOutput(map);
                        }
                        
                        ARFFFileCreator.createARFFData(listDataExtractorOnlyDataBefore, 
                            listDataExtractorOnlyDataAfter, featuresBeforeAfter, 
                            activity, bufferLenght, frequency, "Easy"+counter);
                        counter++;
                    }
                }
            }
        }
        
        writeOutputFiles();
        
        Evaluation evaluationOutput = new Evaluation(generatedFiles);
        evaluationOutput.performEvaluation();
    }
    
    /**
     * After all the files are generated, we write an output file with all the
     * generated ARFF files from the program
     */
    private static void writeOutputFiles()
    {
        File outputFile = new File(FileReader.FOLDER_BASE + "outputFiles.txt");
        try
        {
            if (!outputFile.exists())
            {
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }
            BufferedWriter writer = 
                    new BufferedWriter(new FileWriter(outputFile));
            for (String file: generatedFiles)
            {
                writer.write(file + ";");
            }
            
            writer.flush(); writer.close();
        }
        catch(Exception exc)
        {
            
        }
    }
}
