package arff;

import exerciseanalyser.DataExtractor;
import exerciseanalyser.analyserbeforeafter.DataExtractorBeforeAfter;
import filereader.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import whereismysmartphoneanalyzer.OrderedPowerSet;
import whereismysmartphoneanalyzer.Sensor;
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
    private static final String FOLDER_BEFORE_AFTER = "buffer_before_after" + File.separator;
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
    
    public static void createARFFData(ArrayList<DataExtractor> listExtractorBefore, 
            ArrayList<DataExtractor> listExtractorAfter,
            ArrayList<DataExtractorBeforeAfter> listDataExtractor, 
            String activity, int bufferLength, int frequency, String mapDestination)
    {
        OrderedPowerSet<Sensor> order = new OrderedPowerSet<>(WhereIsMySmartphoneAnalyzer.listSensors);
        for (int i = 1; i < WhereIsMySmartphoneAnalyzer.listSensors.size(); i++)
        {
            List<LinkedHashSet<Sensor>> result = order.getPermutationsList(i);
            
            for (LinkedHashSet<Sensor> list: result) // per ciascuna lista
            {
                boolean[] yesNo = {false, false, false, false, false, false, false, 
                    false, false, false, false, false, false, false};
                
                for (Sensor sensor: list)
                {
                    yesNo[WhereIsMySmartphoneAnalyzer.listSensors.indexOf(sensor)] = true;
                }
                
                /**
                 * We create the output file only if the accelerometer of the 
                 * linear data is included in the combination of features
                 */
                if ((yesNo[0] && !yesNo[1] && !yesNo[2] && !yesNo[3] &&!yesNo[4] && !yesNo[5]) 
                        || 
                    (!yesNo[1] && !yesNo[1] && !yesNo[2] && !yesNo[3] && !yesNo[4] && !yesNo[5])
                        || 
                    (!yesNo[0] && !yesNo[1] && yesNo[2] && !yesNo[3] && !yesNo[4] && !yesNo[5])
                        || 
                    (!yesNo[0] && !yesNo[1] && !yesNo[2] && yesNo[3] && !yesNo[4] && !yesNo[5])
                        || 
                    (!yesNo[0] && !yesNo[1] && !yesNo[2] && !yesNo[3] && yesNo[4] && !yesNo[5]) 
                        || 
                    (!yesNo[0] && !yesNo[1] && !yesNo[2] && !yesNo[3] && !yesNo[4] && yesNo[5]))
                {
                    createARFFBefore(listExtractorBefore, activity, bufferLength, 
                            frequency, yesNo, mapDestination);
                    createARFFAfter(listExtractorAfter, activity, bufferLength, 
                            frequency, yesNo, mapDestination);
                    createARFFBeforeAfter(listDataExtractor, activity, bufferLength, 
                            frequency, yesNo, mapDestination);
                }
            }
        }
    }
    
    private static void createARFFBefore(ArrayList<DataExtractor> listDataExtractor, 
            String activity, int bufferLength, int frequency, boolean[] yesNo, 
            String mapDestination)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        if (activity == null)
        {
            activity = "all";
        }
        if (mapDestination == null)
        {
            mapDestination = "base";
        }
        
        String outputFileString = createOutputName(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], 
            yesNo[12], yesNo[13]);
        
        File outputFile = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT + 
                FOLDER_BEFORE + mapDestination + File.separator + activity 
                + File.separator + outputFileString + File.separator + "Output_" 
                + outputFileString + "_" + bufferLength + "_" + frequency 
                + ".arff");
        
        try
        {
            if (!outputFile.exists())
            {
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(PROLOGUE);
            writer.write(listDataExtractor.get(0)
                    .getPrologueARFF(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], yesNo[12], yesNo[13]));
        
            writer.write(classes);
            writer.write(data);
            
            for (DataExtractor extractor: listDataExtractor)
            {
                writer.write(extractor.dataForAllExercises(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], yesNo[12], yesNo[13]));
            }
            
            writer.flush(); writer.close();
            
            WhereIsMySmartphoneAnalyzer.generatedFiles.add(outputFile.getPath().replace(FileReader.FOLDER_BASE, "")
                .replace(FOLDER_OUTPUT, ""));
        
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    private static void createARFFAfter(ArrayList<DataExtractor> listDataExtractor, 
            String activity, int bufferLength, int frequency, boolean[] yesNo, 
            String mapDestination)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        if (activity == null)
        {
            activity = "all";
        }
        if (mapDestination == null)
        {
            mapDestination = "base";        
        }
        
        String outputFileString = createOutputName(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], 
            yesNo[12], yesNo[13]);
        
        File outputFile = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT + 
                FOLDER_AFTER + mapDestination + File.separator + activity 
                + File.separator + outputFileString + File.separator + "Output_" 
                + outputFileString + "_" + bufferLength + "_" + frequency 
                + ".arff");
        
        try
        {
            if (!outputFile.exists())
            {
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(PROLOGUE);
            writer.write(listDataExtractor.get(0)
                    .getPrologueARFF(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], yesNo[12], yesNo[13]));
        
            writer.write(classes);
            writer.write(data);
            
            for (DataExtractor extractor: listDataExtractor)
            {
                writer.write(extractor.dataForAllExercises(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], yesNo[12], yesNo[13]));
            }
            
            writer.flush(); writer.close();
            WhereIsMySmartphoneAnalyzer.generatedFiles.add(outputFile.getPath()
                .replace(FileReader.FOLDER_BASE, "").replace(FOLDER_OUTPUT, ""));
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    private static void createARFFBeforeAfter(ArrayList<DataExtractorBeforeAfter> listDataExtractor, 
            String activity, int bufferLength, int frequency, boolean[] yesNo, 
            String mapDestination)
    {
        String classes = createClassesOutput();
        String data = "@DATA" + System.getProperty("line.separator");
        if (activity == null)
        {
            activity = "all";
        }
        if (mapDestination == null)
        {
            mapDestination = "all";
        }
        
        String outputFileString = createOutputName(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], 
            yesNo[12], yesNo[13]);
        
        File outputFile = new File(FileReader.FOLDER_BASE + FOLDER_OUTPUT + 
                FOLDER_BEFORE_AFTER + mapDestination + File.separator + activity 
                + File.separator + outputFileString + File.separator + "Output_" 
                + outputFileString + "_" + bufferLength + "_" + frequency 
                + ".arff");
        
        try
        {
            if (!outputFile.exists())
            {
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(PROLOGUE);
            writer.write(listDataExtractor.get(0)
                    .buildFeaturesListForARFF(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], yesNo[12], yesNo[13]));
        
            writer.write(classes);
            writer.write(data);
            
            for (DataExtractorBeforeAfter extractor: listDataExtractor)
            {
                writer.write(extractor.dataForAllExercises(yesNo[0], yesNo[1], yesNo[2], 
                yesNo[3], yesNo[4], yesNo[5], yesNo[6], yesNo[7], yesNo[8], 
                yesNo[9], yesNo[10], yesNo[11], yesNo[12], yesNo[13]));
            }
            
            writer.flush(); writer.close();
            WhereIsMySmartphoneAnalyzer.generatedFiles.add(outputFile.getPath().replace(FileReader.FOLDER_BASE, "")
                .replace(FOLDER_OUTPUT, ""));
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    private static String createOutputName(boolean accelerometer, 
            boolean accelerometerRotated, boolean accelerometerNoGravity, 
            boolean accelerometerNoGravityRotated, boolean linear, 
            boolean linearRotated, boolean rotation, boolean gravity, 
            boolean gyroscope, boolean magneticField, boolean ambientTemperature, 
            boolean light, boolean pressure, boolean relativeHumidity)
    {
        String output = "";
        
        if (accelerometer)
        {
            output += "Accelerometer_";
        }
        if (accelerometerRotated)
        {
            output += "AccelerometerRotated_";
        }
        if (accelerometerNoGravity)
        {
            output += "AccelerometerNoGravity_";
        }
        if (accelerometerNoGravityRotated)
        {
            output += "AccelerometerNoGravityRotated_";
        }
        if (linear)
        {
            output += "Linear_";
        }
        if (linearRotated)
        {
            output += "LinearRotated_";
        }
        if (rotation)
        {
            output += "Rotation_";
        }
        if (gravity)
        {
            output += "Gravity_";
        }
        if (gyroscope)
        {
            output += "Gyroscope_";
        }
        if (magneticField)
        {
            output += "MagneticField_";
        }
        if (ambientTemperature)
        {
            output += "AmbientTemperature_";
        }
        if (light)
        {
            output += "Light_";
        }
        if (pressure)
        {
            output += "Pressure_";
        }
        if (relativeHumidity)
        {
            output += "RelativeHumidity_";
        }
        
        return output;
    }
}
