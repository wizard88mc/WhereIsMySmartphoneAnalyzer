package arff;

import filereader.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import whereismysmartphoneanalyzer.DataExtractorOnlyDataBefore;
import whereismysmartphoneanalyzer.WhereIsMySmartphoneAnalyzer;

/**
 *
 * @author matteo
 */
public class ARFFFileCreator 
{
    private static final String FOLDER_OUTPUT = "output" + File.separator;
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
     * Creates the ARFF data for accelerometer data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFAccelerometerDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileAccelerometer = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Accelerometer" + File.separator + "Output_Accelerometer_" + 
                    bufferLength + "_" + frequency + 
                    ".arff"), 
                outputFileAccelerometerRotated = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Accelerometer_Rotated" + File.separator + "Output_Accelerometer_Rotated_" 
                    + bufferLength + "_" + frequency + ".arff"),
                outputFileAccelerometerNoGravity = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Accelerometer_No_Gravity" + File.separator + "Output_Accelerometer_No_Gravity_" 
                    + bufferLength + "_" + frequency + ".arff"),
                outputFileAccelerometerNoGravityRotated = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Accelerometer_No_Gravity_Rotated" + File.separator
                    + "Output_Accelerometer_No_Gravity_Rotated_" + bufferLength + "_" + frequency + ".arff");
            
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

            writerAccelerometer.write(listDataExtractorOnlyDataBefore.get(0).getAccelerometerPrologueARFF());
            writerAccelerometerRotated.write(listDataExtractorOnlyDataBefore.get(0).getAccelerometerRotatedPrologueARFF());
            writerAccelerometerNoGravity.write(listDataExtractorOnlyDataBefore.get(0).getAccelerometerNoGravityPrologueARFF());
            writerAccelerometerNoGravityRotated.write(listDataExtractorOnlyDataBefore.get(0).getAccelerometerNoGravityRotatedPrologueARFF());
            
            writerAccelerometer.write(classes);
            writerAccelerometerRotated.write(classes);
            writerAccelerometerNoGravity.write(classes);
            writerAccelerometerNoGravityRotated.write(classes);
            
            writerAccelerometer.write(data);
            writerAccelerometerRotated.write(data);
            writerAccelerometerNoGravity.write(data);
            writerAccelerometerNoGravityRotated.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerAccelerometer.write(extractor.dataForAllExercises(true, false, false, false, false, false, false, false, false, false, false, false, false, false));
                writerAccelerometerRotated.write(extractor.dataForAllExercises(false, true, false, false, false, false, false, false, false, false, false, false, false, false));
                writerAccelerometerNoGravity.write(extractor.dataForAllExercises(false, false, true, false, false, false, false, false, false, false, false, false, false, false));
                writerAccelerometerNoGravityRotated.write(extractor.dataForAllExercises(false, false, false, true, false, false, false, false, false, false, false, false, false, false));
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
     * Creates the ARFF data for linear data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFLinearDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileLinear = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Linear" + File.separator + "Output_Linear_" + 
                    bufferLength + "_" + frequency + 
                    ".arff"), 
                outputFileLinearRotated = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Linear_Rotated" + File.separator + "Output_Linear_Rotated_" 
                    + bufferLength + "_" + frequency + ".arff");
            
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

            writerLinear.write(listDataExtractorOnlyDataBefore.get(0).getLinearPrologueARFF());
            writerLinearRotated.write(listDataExtractorOnlyDataBefore.get(0).getLinearRotatedPrologueARFF());
            
            writerLinear.write(classes);
            writerLinearRotated.write(classes);
            
            writerLinear.write(data);
            writerLinearRotated.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerLinear.write(extractor.dataForAllExercises(false, false, false, false, true, false, false, false, false, false, false, false, false, false));
                writerLinearRotated.write(extractor.dataForAllExercises(false, false, false, false, false, true, false, false, false, false, false, false, false, false));
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
     * Creates the ARFF data for rotation data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFRotationDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileRotation = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Rotation" + File.separator + "Output_Rotation_" + 
                    bufferLength + "_" + frequency + 
                    ".arff");
            
            if (!outputFileRotation.exists())
            {
                outputFileRotation.getParentFile().mkdirs();
                outputFileRotation.createNewFile();
            }
                    
            BufferedWriter writerRotation = 
                        new BufferedWriter(new FileWriter(outputFileRotation));

            writerRotation.write(listDataExtractorOnlyDataBefore.get(0).getRotationPrologueARFF());
            
            writerRotation.write(classes);
            
            writerRotation.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerRotation.write(extractor.dataForAllExercises(false, false, false, false, false, false, true, false, false, false, false, false, false, false));
            }
            
            writerRotation.flush(); writerRotation.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for gravity data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFGravityDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileGravity = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Gravity" + File.separator + "Output_Gravity_" + 
                    bufferLength + "_" + frequency + 
                    ".arff");
            
            if (!outputFileGravity.exists())
            {
                outputFileGravity.getParentFile().mkdirs();
                outputFileGravity.createNewFile();
            }
                    
            BufferedWriter writerGravity = 
                        new BufferedWriter(new FileWriter(outputFileGravity));

            writerGravity.write(listDataExtractorOnlyDataBefore.get(0).getLinearPrologueARFF());
            
            writerGravity.write(classes);
            
            writerGravity.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerGravity.write(extractor.dataForAllExercises(false, false, false, false, false, false, false, true, false, false, false, false, false, false));
            }
            
            writerGravity.flush(); writerGravity.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for gyroscope data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFGyroscopeDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileGyroscope = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Gyroscope" + File.separator + "Output_Gyroscope_" + 
                    bufferLength + "_" + frequency + 
                    ".arff");
            
            if (!outputFileGyroscope.exists())
            {
                outputFileGyroscope.getParentFile().mkdirs();
                outputFileGyroscope.createNewFile();
            }
                    
            BufferedWriter writerGyroscope = 
                        new BufferedWriter(new FileWriter(outputFileGyroscope));

            writerGyroscope.write(listDataExtractorOnlyDataBefore.get(0).getLinearPrologueARFF());
            
            writerGyroscope.write(classes);
            
            writerGyroscope.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerGyroscope.write(extractor.dataForAllExercises(false, false, false, false, false, false, false, false, true, false, false, false, false, false));
            }
            
            writerGyroscope.flush(); writerGyroscope.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for magnetic field data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFMagneticFieldDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileMagneticField = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "MagneticField" + File.separator + "Output_MagneticField_" + 
                    bufferLength + "_" + frequency + 
                    ".arff");
            
            if (!outputFileMagneticField.exists())
            {
                outputFileMagneticField.getParentFile().mkdirs();
                outputFileMagneticField.createNewFile();
            }
                    
            BufferedWriter writerMagneticField = 
                        new BufferedWriter(new FileWriter(outputFileMagneticField));

            writerMagneticField.write(listDataExtractorOnlyDataBefore.get(0).getLinearPrologueARFF());
            
            writerMagneticField.write(classes);
            
            writerMagneticField.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerMagneticField.write(extractor.dataForAllExercises(false, false, false, false, false, false, false, false, false, true, false, false, false, false));
            }
            
            writerMagneticField.flush(); writerMagneticField.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for ambient temperature data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFAmbientTemperatureDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileAmbientTemperature = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "AmbientTemperature" + File.separator + "Output_AmbientTemperature_" + 
                    bufferLength + "_" + frequency + 
                    ".arff");
            
            if (!outputFileAmbientTemperature.exists())
            {
                outputFileAmbientTemperature.getParentFile().mkdirs();
                outputFileAmbientTemperature.createNewFile();
            }
                    
            BufferedWriter writerAmbientTemperature = 
                        new BufferedWriter(new FileWriter(outputFileAmbientTemperature));

            writerAmbientTemperature.write(listDataExtractorOnlyDataBefore.get(0).getLinearPrologueARFF());
            
            writerAmbientTemperature.write(classes);
            
            writerAmbientTemperature.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerAmbientTemperature.write(extractor.dataForAllExercises(false, false, false, false, false, false, false, false, false, false, true, false, false, false));
            }
            
            writerAmbientTemperature.flush(); writerAmbientTemperature.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for light data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFLightDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileLight = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Light" + File.separator + "Output_Light_" + 
                    bufferLength + "_" + frequency + 
                    ".arff");
            
            if (!outputFileLight.exists())
            {
                outputFileLight.getParentFile().mkdirs();
                outputFileLight.createNewFile();
            }
                    
            BufferedWriter writerLight = 
                        new BufferedWriter(new FileWriter(outputFileLight));

            writerLight.write(listDataExtractorOnlyDataBefore.get(0).getLinearPrologueARFF());
            
            writerLight.write(classes);
            
            writerLight.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerLight.write(extractor.dataForAllExercises(false, false, false, false, false, false, false, false, false, false, false, true, false, false));
            }
            
            writerLight.flush(); writerLight.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for pressure data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFPressureDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFilePressure = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "Pressure" + File.separator + "Output_Pressure_" + 
                    bufferLength + "_" + frequency + 
                    ".arff");
            
            if (!outputFilePressure.exists())
            {
                outputFilePressure.getParentFile().mkdirs();
                outputFilePressure.createNewFile();
            }
                    
            BufferedWriter writerPressure = 
                        new BufferedWriter(new FileWriter(outputFilePressure));

            writerPressure.write(listDataExtractorOnlyDataBefore.get(0).getLinearPrologueARFF());
            
            writerPressure.write(classes);
            
            writerPressure.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerPressure.write(extractor.dataForAllExercises(false, false, false, false, false, false, false, false, false, false, false, false, true, false));
            }
            
            writerPressure.flush(); writerPressure.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    /**
     * Creates the ARFF data for relative humidity data only with data before the
     * proximity sensor changes state
     * @param listDataExtractorOnlyDataBefore a list of data analyzer only for 
     * data before the proximity sensor starts
     * @param bufferLength the length of the buffer
     * @param frequency the data frequency
     */
    public static void createARFFRelativeHumidityDataOnlyBefore(ArrayList<DataExtractorOnlyDataBefore> 
            listDataExtractorOnlyDataBefore, int bufferLength, int frequency)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        try 
        {
            File outputFileRelativeHumidity = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT
                    + "RelativeHumidity" + File.separator + "Output_RelativeHumidity_" + 
                    bufferLength + "_" + frequency + 
                    ".arff");
            
            if (!outputFileRelativeHumidity.exists())
            {
                outputFileRelativeHumidity.getParentFile().mkdirs();
                outputFileRelativeHumidity.createNewFile();
            }
                    
            BufferedWriter writerRelativeHumidity = 
                        new BufferedWriter(new FileWriter(outputFileRelativeHumidity));

            writerRelativeHumidity.write(listDataExtractorOnlyDataBefore.get(0).getLinearPrologueARFF());
            
            writerRelativeHumidity.write(classes);
            
            writerRelativeHumidity.write(data);
            for (DataExtractorOnlyDataBefore extractor: listDataExtractorOnlyDataBefore)
            {
                writerRelativeHumidity.write(extractor.dataForAllExercises(false, false, false, false, false, false, false, false, false, false, false, false, false, true));
            }
            
            writerRelativeHumidity.flush(); writerRelativeHumidity.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
