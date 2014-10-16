package filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class is responsible to read a file and extract 
 * @author Matteo Ciman
 */
public class FileReader {
    
    public static final String FOLDER_BASE = "data" + File.separator 
            + "extensive" + File.separator;
    
    protected File file = null;
    protected BufferedReader reader = null;
    
    public FileReader(String folder, String filename) 
    {
        file = new File(FOLDER_BASE + folder + filename + ".csv");
        
    }
    
    /**
     * Creates a new BufferedReader object to read the specified file
     * @throws FileNotFoundException is the file is not found
     */
    public void openFile() throws FileNotFoundException
    {
        reader = new BufferedReader(new java.io.FileReader(file));
    }
    
    /**
     * Reads another line from the input file
     * @return the line if any, null otherwise
     * @throws java.io.IOException 
     */
    public String readLine() throws IOException
    {
        return reader.readLine();
    }
    
    /**
     * Closes the BufferedReader
     */
    public void closeFile()
    {
        try 
        {
            reader.close();
        }
        catch(IOException exc)
        {
            System.out.println(exc.toString());
            exc.printStackTrace();
        }
    }
}
