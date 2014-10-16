package filereader;

import java.io.File;
import java.util.ArrayList;
import models.Exercise;

/**
 * class SettingsReader
 * 
 * This class is responsible to read all the CSV lines that represent a particular
 * exercise, with all the settings of the exercise and the trunk number 
 * 
 * @author  Matteo Ciman
 * @version 0.1
 * @since   2014-10-17
 */
public class SettingsReader extends FileReader
{

    private static final String FOLDER = "settings";
    
    public SettingsReader(String IMEI, String timestamp) {
        super(FOLDER + File.separator, IMEI + "_WhereIsMySmartphoneSettings_" + timestamp);
    }
    
    /**
     * It retrieves all the trunks (and its settings) that are stored in a 
     * Settings file
     * 
     * @return a List of Exercise objects
     */
    public ArrayList<Exercise> retrieveTrunks()
    {
        ArrayList<Exercise> trunks = new ArrayList<>();
        
        try
        {
            openFile();
            String line;
            
            while ((line = readLine()) != null)
            {
                // The current line is a trunk
                String[] elements = line.replace("(", "").replace(")", "").split(",");
                
                /**
                 * elements[0]: trunk ID accelerometer
                 * elements[1]: trunk ID linear
                 * elements[2]: sex
                 * elements[3]: age
                 * elements[4]: height
                 * elements[5]: shoes
                 * elements[6]: hand
                 * elements[7]: action
                 * elements[8]: origin
                 * elements[9]: destination
                 */
                trunks.add(new Exercise(Integer.valueOf(elements[0]), 
                    Integer.valueOf(elements[1]), elements[2], elements[3], 
                    elements[4], elements[5], elements[6], elements[7], 
                    elements[8], elements[9]));
            }
        }
        catch(Exception exc)
        {
            System.out.println(exc.toString());
            exc.printStackTrace();
        }
        return trunks;
    }
    
}
