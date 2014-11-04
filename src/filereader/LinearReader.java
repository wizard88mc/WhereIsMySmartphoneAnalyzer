/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import models.Reading;

/**
 *
 * @author Matteo Ciman
 */
public class LinearReader extends FileReader
{

    private static final String FOLDER = "linear";
    
    public LinearReader(String IMEI, String timestamp) 
    {
        super(FOLDER + File.separator, IMEI + "_WhereIsMySmartphoneLinear_" 
                + timestamp);
    }
    
    public ArrayList<Reading> getListReadings()
    {
        ArrayList<Reading> readings = new ArrayList<>();
        
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
