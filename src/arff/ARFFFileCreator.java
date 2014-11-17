package arff;

import exerciseanalyser.DataExtractor;
import filereader.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import exerciseanalyser.analysisbefore.DataExtractorOnlyDataBefore;
import whereismysmartphoneanalyzer.WhereIsMySmartphoneAnalyzer;

/**
 *
 * @author matteo
 */
public class ARFFFileCreator 
{
    private static final String PROLOGUE = "@RELATION WhereIsSmartphone" + 
                System.getProperty("line.separator");
    private static final String FOLDER_OUTPUT = "output" + File.separator;
    private static final String FOLDER_BEFORE = "buffer_before" + File.separator;
    private static final String FOLDER_AFTER = "buffer_after" + File.separator;
    private static String createClassesOutput()
    {
        String classes = "{";
        for (String classToAdd: WhereIsMySmartphoneAnalyzer.listDestinations)
        {
            classes += classToAdd + ",";
        }
        return "@ATTRIBUTE class " + classes.substring(0, classes.length() - 1) 
                + "}" + System.getProperty("line.separator");
    }
    
    /**
     * Prints all the ARFF files data for data built using readings before the 
     * proximity sensor changes to 0
     * @param listDataExtractor a list if DataExtractor (both Before or After)
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFDataOnlyBefore(ArrayList<DataExtractor> listDataExtractor,
            int bufferLength, int frequency)
    {
        createARFFAccelerometerData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFLinearData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFRotationData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFGravity(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFGyroscopeData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFMagneticFieldData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFAmbientTemperatureData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFLightData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFPressureData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
        createARFFRelativeHumidityData(listDataExtractor, bufferLength, frequency, FOLDER_BEFORE);
    }
    
    /**
     * Prints all the ARFF files data for data built using readings after the 
     * proximity sensor changes to 0
     * @param listDataExtractor
     * @param bufferLength
     * @param frequency 
     */
    public static void createARFFDataOnlyAfter(ArrayList<DataExtractor> listDataExtractor, 
            int bufferLength, int frequency)
    {
        createARFFAccelerometerData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFLinearData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFRotationData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFGravity(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFGyroscopeData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFMagneticFieldData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFAmbientTemperatureData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFLightData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFPressureData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
        createARFFRelativeHumidityData(listDataExtractor, bufferLength, frequency, FOLDER_AFTER);
    }
    
    /**
     * Creates the ARFF data for accelerometer data only with data before the
     * proximity sensor changes state
     * @param listDataExtractor a list of data analyzer 
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the subfolder where output data
     */
    public static void createARFFAccelerometerData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileAccelerometer = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Accelerometer" + File.separator 
                    + "Output_Accelerometer_" + bufferLength + "_" + frequency + 
                    ".arff"), 
                outputFileAccelerometerRotated = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Accelerometer_Rotated" + File.separator 
                    + "Output_Accelerometer_Rotated_" + bufferLength + "_" 
                    + frequency + ".arff"),
                outputFileAccelerometerNoGravity = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Accelerometer_No_Gravity" + File.separator 
                    + "Output_Accelerometer_No_Gravity_" + bufferLength + "_" 
                    + frequency + ".arff"),
                outputFileAccelerometerNoGravityRotated = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Accelerometer_No_Gravity_Rotated" + File.separator
                    + "Output_Accelerometer_No_Gravity_Rotated_" + bufferLength 
                    + "_" + frequency + ".arff");
            
            if (!outputFileAccelerometer.exists())
            {
                outputFileAccelerometer.getParentFile().mkdirs();
                outputFileAccelerometer.createNewFile();
            }
            if (!outputFileAccelerometerRotated.exists())
            {
                outputFileAccelerometerRotated.getParentFile().mkdirs();
                outputFileAccelerometerRotated.createNewFile();
            }
            if (!outputFileAccelerometerNoGravity.exists())
            {
                outputFileAccelerometerNoGravity.getParentFile().mkdirs();
                outputFileAccelerometerNoGravity.createNewFile();
            }
            if (!outputFileAccelerometerNoGravityRotated.exists())
            {
                outputFileAccelerometerNoGravityRotated.getParentFile().mkdirs();
                outputFileAccelerometerNoGravityRotated.createNewFile();
            }
                    
            BufferedWriter writerAccelerometer = new BufferedWriter(new FileWriter(outputFileAccelerometer)),
                    writerAccelerometerRotated = new BufferedWriter(new FileWriter(outputFileAccelerometerRotated)),
                    writerAccelerometerNoGravity = new BufferedWriter(new FileWriter(outputFileAccelerometerNoGravity)),
                    writerAccelerometerNoGravityRotated = new BufferedWriter(new FileWriter(outputFileAccelerometerNoGravityRotated));

            writerAccelerometer.write(PROLOGUE); writerAccelerometerRotated.write(PROLOGUE);
            writerAccelerometerNoGravity.write(PROLOGUE); writerAccelerometerNoGravityRotated.write(PROLOGUE);
            writerAccelerometer.write(listDataExtractor.get(0).getAccelerometerPrologueARFF());
            writerAccelerometerRotated.write(listDataExtractor.get(0).getAccelerometerRotatedPrologueARFF());
            writerAccelerometerNoGravity.write(listDataExtractor.get(0).getAccelerometerNoGravityPrologueARFF());
            writerAccelerometerNoGravityRotated.write(listDataExtractor.get(0).getAccelerometerNoGravityRotatedPrologueARFF());
            
            writerAccelerometer.write(classes);
            writerAccelerometerRotated.write(classes);
            writerAccelerometerNoGravity.write(classes);
            writerAccelerometerNoGravityRotated.write(classes);
            
            writerAccelerometer.write(data);
            writerAccelerometerRotated.write(data);
            writerAccelerometerNoGravity.write(data);
            writerAccelerometerNoGravityRotated.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerAccelerometer.write(extractor.dataForAllExercises(true, 
                        false, false, false, false, false, false, false, false, 
                        false, false, false, false, false));
                writerAccelerometerRotated.write(extractor.dataForAllExercises(false, 
                        true, false, false, false, false, false, false, false, 
                        false, false, false, false, false));
                writerAccelerometerNoGravity.write(extractor.dataForAllExercises(false, 
                        false, true, false, false, false, false, false, false, 
                        false, false, false, false, false));
                writerAccelerometerNoGravityRotated.write(extractor.dataForAllExercises(false, 
                        false, false, true, false, false, false, false, false, 
                        false, false, false, false, false));
            }
            
            writerAccelerometer.flush(); writerAccelerometer.close();
            writerAccelerometerRotated.flush(); writerAccelerometerRotated.close();
            writerAccelerometerNoGravity.flush(); writerAccelerometerNoGravity.close();
            writerAccelerometerNoGravityRotated.flush(); writerAccelerometerNoGravityRotated.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for linear data 
     * @param listDataExtractor a list of data analyzer 
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the subfolder where output data
     */
    public static void createARFFLinearData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileLinear = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Linear" + File.separator + "Output_Linear_" + 
                    bufferLength + "_" + frequency + 
                    ".arff"), 
                outputFileLinearRotated = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Linear_Rotated" + File.separator
                    + "Output_Linear_Rotated_" + bufferLength + "_" + frequency 
                    + ".arff");
            
            if (!outputFileLinear.exists())
            {
                outputFileLinear.getParentFile().mkdirs();
                outputFileLinear.createNewFile();
            }
            if (!outputFileLinearRotated.exists())
            {
                outputFileLinearRotated.getParentFile().mkdirs();
                outputFileLinearRotated.createNewFile();
            }
                    
            BufferedWriter writerLinear = 
                        new BufferedWriter(new FileWriter(outputFileLinear)),
                    writerLinearRotated = 
                        new BufferedWriter(new FileWriter(outputFileLinearRotated));

            writerLinear.write(PROLOGUE); writerLinear.write(PROLOGUE);
            writerLinear.write(listDataExtractor.get(0).getLinearPrologueARFF());
            writerLinearRotated.write(listDataExtractor.get(0).getLinearRotatedPrologueARFF());
            
            writerLinear.write(classes);
            writerLinearRotated.write(classes);
            
            writerLinear.write(data);
            writerLinearRotated.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerLinear.write(extractor.dataForAllExercises(false, false, 
                        false, false, true, false, false, false, false, false, 
                        false, false, false, false));
                writerLinearRotated.write(extractor.dataForAllExercises(false, 
                        false, false, false, false, true, false, false, false, 
                        false, false, false, false, false));
            }
            
            writerLinear.flush(); writerLinear.close();
            writerLinearRotated.flush(); writerLinearRotated.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for rotation data 
     * @param listDataExtractor a list of data analyzer 
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the subfolder for the data
     */
    public static void createARFFRotationData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileRotation = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Rotation" + File.separator + "Output_Rotation_" 
                    + bufferLength + "_" + frequency + ".arff");
            
            if (!outputFileRotation.exists())
            {
                outputFileRotation.getParentFile().mkdirs();
                outputFileRotation.createNewFile();
            }
                    
            BufferedWriter writerRotation = 
                        new BufferedWriter(new FileWriter(outputFileRotation));

            writerRotation.write(PROLOGUE);
            writerRotation.write(listDataExtractor.get(0).getRotationPrologueARFF());
            
            writerRotation.write(classes);
            
            writerRotation.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerRotation.write(extractor.dataForAllExercises(false, false, 
                        false, false, false, false, true, false, false, false, 
                        false, false, false, false));
            }
            
            writerRotation.flush(); writerRotation.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for gravity data 
     * @param listDataExtractor a list of data analyzer 
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the subfolder to output the data 
     */
    public static void createARFFGravity(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, 
            String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileGravity = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Gravity" + File.separator + "Output_Gravity_" 
                    + bufferLength + "_" + frequency + ".arff");
            
            if (!outputFileGravity.exists())
            {
                outputFileGravity.getParentFile().mkdirs();
                outputFileGravity.createNewFile();
            }
                    
            BufferedWriter writerGravity = 
                        new BufferedWriter(new FileWriter(outputFileGravity));

            writerGravity.write(PROLOGUE);
            writerGravity.write(listDataExtractor.get(0).getGravityPrologueARFF());
            
            writerGravity.write(classes);
            
            writerGravity.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerGravity.write(extractor.dataForAllExercises(false, false, 
                        false, false, false, false, false, true, false, false, 
                        false, false, false, false));
            }
            
            writerGravity.flush(); writerGravity.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for gyroscope data 
     * @param listDataExtractor a list of data analyzer 
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the sub folder where to output data
     */
    public static void createARFFGyroscopeData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, 
            String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileGyroscope = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Gyroscope" + File.separator + "Output_Gyroscope_" 
                    + bufferLength + "_" + frequency + ".arff");
            
            if (!outputFileGyroscope.exists())
            {
                outputFileGyroscope.getParentFile().mkdirs();
                outputFileGyroscope.createNewFile();
            }
                    
            BufferedWriter writerGyroscope = 
                        new BufferedWriter(new FileWriter(outputFileGyroscope));

            writerGyroscope.write(PROLOGUE);
            writerGyroscope.write(listDataExtractor.get(0).getGyroscopePrologueARFF());
            
            writerGyroscope.write(classes);
            
            writerGyroscope.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerGyroscope.write(extractor.dataForAllExercises(false, false, 
                        false, false, false, false, false, false, true, false, 
                        false, false, false, false));
            }
            
            writerGyroscope.flush(); writerGyroscope.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for magnetic field data 
     * @param listDataExtractor a list of data analyzer
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the subfolder to output data
     */
    protected static void createARFFMagneticFieldData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, 
            String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileMagneticField = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "MagneticField" + File.separator 
                    + "Output_MagneticField_" + bufferLength + "_" + frequency 
                    + ".arff");
            
            if (!outputFileMagneticField.exists())
            {
                outputFileMagneticField.getParentFile().mkdirs();
                outputFileMagneticField.createNewFile();
            }
                    
            BufferedWriter writerMagneticField = 
                        new BufferedWriter(new FileWriter(outputFileMagneticField));

            writerMagneticField.write(PROLOGUE);
            writerMagneticField.write(listDataExtractor.get(0).getMagneticFieldPrologueARFF());
            
            writerMagneticField.write(classes);
            
            writerMagneticField.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerMagneticField.write(extractor.dataForAllExercises(false, 
                        false, false, false, false, false, false, false, false, 
                        true, false, false, false, false));
            }
            
            writerMagneticField.flush(); writerMagneticField.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for ambient temperature data 
     * @param listDataExtractor a list of data analyzer
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the sub folder where output data
     */
    private static void createARFFAmbientTemperatureData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, 
            String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileAmbientTemperature = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "AmbientTemperature" + File.separator 
                    + "Output_AmbientTemperature_" + bufferLength + "_" + frequency 
                    + ".arff");
            
            if (!outputFileAmbientTemperature.exists())
            {
                outputFileAmbientTemperature.getParentFile().mkdirs();
                outputFileAmbientTemperature.createNewFile();
            }
                    
            BufferedWriter writerAmbientTemperature = 
                        new BufferedWriter(new FileWriter(outputFileAmbientTemperature));

            writerAmbientTemperature.write(PROLOGUE);
            writerAmbientTemperature.write(listDataExtractor.get(0).getAmbientTemperaturePrologueARFF());
            
            writerAmbientTemperature.write(classes);
            
            writerAmbientTemperature.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerAmbientTemperature.write(extractor.dataForAllExercises(false, 
                        false, false, false, false, false, false, false, false, 
                        false, true, false, false, false));
            }
            
            writerAmbientTemperature.flush(); writerAmbientTemperature.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for light data  
     * @param listDataExtractor a list of data analyzer
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the sub folder where output data
     */
    private static void createARFFLightData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, 
            String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileLight = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Light" + File.separator + "Output_Light_" 
                    + bufferLength + "_" + frequency + ".arff");
            
            if (!outputFileLight.exists())
            {
                outputFileLight.getParentFile().mkdirs();
                outputFileLight.createNewFile();
            }
                    
            BufferedWriter writerLight = 
                        new BufferedWriter(new FileWriter(outputFileLight));

            writerLight.write(PROLOGUE);
            writerLight.write(listDataExtractor.get(0).getLightPrologueARFF());
            
            writerLight.write(classes);
            
            writerLight.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerLight.write(extractor.dataForAllExercises(false, false, 
                        false, false, false, false, false, false, false, false, 
                        false, true, false, false));
            }
            
            writerLight.flush(); writerLight.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for pressure data  
     * @param listDataExtractor a list of data analyzer
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the sub folder for the output data
     */
    private static void createARFFPressureData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, 
            String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFilePressure = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "Pressure" + File.separator + "Output_Pressure_" 
                    + bufferLength + "_" + frequency + ".arff");
            
            if (!outputFilePressure.exists())
            {
                outputFilePressure.getParentFile().mkdirs();
                outputFilePressure.createNewFile();
            }
                    
            BufferedWriter writerPressure = 
                        new BufferedWriter(new FileWriter(outputFilePressure));

            writerPressure.write(PROLOGUE);
            writerPressure.write(listDataExtractor.get(0).getPressurePrologueARFF());
            
            writerPressure.write(classes);
            
            writerPressure.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerPressure.write(extractor.dataForAllExercises(false, false, 
                        false, false, false, false, false, false, false, false, 
                        false, false, true, false));
            }
            
            writerPressure.flush(); writerPressure.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for relative humidity data  
     * @param listDataExtractor a list of data analyzer 
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     * @param subfolder the sub folder where output data
     */
    private static void createARFFRelativeHumidityData(ArrayList<DataExtractor> 
            listDataExtractor, int bufferLength, int frequency, 
            String subfolder)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileRelativeHumidity = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + subfolder + "RelativeHumidity" + File.separator 
                    + "Output_RelativeHumidity_" + bufferLength + "_" + frequency 
                    + ".arff");
            
            if (!outputFileRelativeHumidity.exists())
            {
                outputFileRelativeHumidity.getParentFile().mkdirs();
                outputFileRelativeHumidity.createNewFile();
            }
                    
            BufferedWriter writerRelativeHumidity = 
                        new BufferedWriter(new FileWriter(outputFileRelativeHumidity));

            writerRelativeHumidity.write(PROLOGUE);
            writerRelativeHumidity.write(listDataExtractor.get(0).getRelativeHumidityPrologueARFF());
            
            writerRelativeHumidity.write(classes);
            
            writerRelativeHumidity.write(data);
            for (DataExtractor extractor: listDataExtractor)
            {
                writerRelativeHumidity.write(extractor.dataForAllExercises(false, 
                        false, false, false, false, false, false, false, false, 
                        false, false, false, false, true));
            }
            
            writerRelativeHumidity.flush(); writerRelativeHumidity.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
