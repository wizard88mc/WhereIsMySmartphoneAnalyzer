package filereader;

import java.io.File;
import java.util.ArrayList;
import models.Reading;

/**
 *
 * @author Matteo Ciman
 */
public class AccelerometerReader extends FileReader
{

    private static final String FOLDER = "accelerometer";
    
    public AccelerometerReader(String IMEI, String timestamp) 
    {
        super(FOLDER + File.separator, IMEI + "_WhereIsMySmartphoneAccelerometer_" 
                + timestamp);
    }
    
    public ArrayList<Reading> getListReadings()
    {
        ArrayList<Reading> readings = new ArrayList<Reading>();
        
        try 
        {
            openFile();
            String line;
            while ((line = readLine()) != null)
            {
                readings.add(new Reading(line));
            }
        }
        catch(Exception exc)
        {
            System.out.println(exc);
            exc.printStackTrace();
        }
        
        return readings;
    }
    
}
