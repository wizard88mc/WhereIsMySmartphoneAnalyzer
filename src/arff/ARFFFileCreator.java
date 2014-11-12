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
            File outputFileAccelerometer = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT + 
                    "Output_Accelerometer_" + bufferLength + "_" + frequency + 
                    ".arff"), 
                outputFileAccelerometerRotated = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT + 
                    "Output_Accelerometer_Rotated_" + bufferLength + "_" + frequency + ".arff"),
                outputFileAccelerometerNoGravity = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT + 
                    "Output_Accelerometer_No_Gravity_" + bufferLength + "_" + frequency + ".arff"),
                outputFileAccelerometerNoGravityRotated = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT + 
                    "Output_Accelerometer_No_Gravity_Rotated_" + bufferLength + "_" + frequency + ".arff");
            
            if (!outputFileAccelerometer.exists())
            {
                outputFileAccelerometer.createNewFile();
            }
            if (!outputFileAccelerometerRotated.exists())
            {
                outputFileAccelerometerRotated.createNewFile();
            }
            if (!outputFileAccelerometerNoGravity.exists())
            {
                outputFileAccelerometerNoGravity.createNewFile();
            }
            if (!outputFileAccelerometerNoGravityRotated.exists())
            {
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
    
    
}
