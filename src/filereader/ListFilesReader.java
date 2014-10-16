package filereader;

import java.util.ArrayList;

/**
 * class listFilesReader
 * 
 * This class is responsible to read all the couples of IMEI,timestamp in the 
 * InputFiles.csv file that contains information about the file that are in 
 * the related folder
 * 
 * @author  Matteo Ciman
 * @version 0.1
 * @since   2014-10-17
 */
public class ListFilesReader extends FileReader
{

    public ListFilesReader() {
        super("", "InputFiles");
    }
    
    /**
     * Retrieves all the couples (IMEI, timestamp) that represents a file to be
     * used to retrieve data
     * 
     * @return a list of IMEI,timestamp couples 
     */
    public ArrayList<String> readListFiles()
    {
        ArrayList<String> listFiles = new ArrayList<>();
        
        try 
        {
            openFile();
            String line;
            while ((line = readLine()) != null)
            {
                listFiles.add(line.replace("(", "").replace(")", ""));
            }
        }
        catch(Exception exc)
        {
            System.out.println(exc.toString());
            exc.printStackTrace();
        }
        return listFiles;
    }
    
}
