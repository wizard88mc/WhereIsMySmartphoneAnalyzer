package weka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author matteo
 */
public class Evaluation 
{
    private ArrayList<String> listFiles;
    
    public Evaluation(ArrayList<String> listFiles)
    {
        this.listFiles = listFiles;
    }
    
    public void performEvaluation()
    {
        try
        {
            File outputFile = new File(filereader.FileReader.FOLDER_BASE + "evaluation" + 
                    File.separator + "globalEvaluation.txt");
            
            if (!outputFile.exists())
            {
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            
            for (String file: listFiles)
            {
                try
                {
                    DataSource source = new DataSource(filereader.FileReader.FOLDER_BASE + 
                            "output" + File.separator + file);
                    Instances data = source.getDataSet();

                    if (data.classIndex() == -1)
                    {
                        data.setClassIndex(data.numAttributes() - 1);
                    }

                    J48 tree = new J48();
                    tree.buildClassifier(data);
                    weka.classifiers.Evaluation eval = new weka.classifiers.Evaluation(data);
                    eval.crossValidateModel(tree, data, 10, new Random(1));
                    
                    writer.write(file + System.getProperty("line.separator"));
                    writer.write("TREE" + System.getProperty("line.separator"));
                    writer.write(eval.toClassDetailsString() + System.getProperty("line.separator"));
                    
                    for (int i = 3; i < 8; i++)
                    {
                        IBk knn = new IBk(i);
                        knn.buildClassifier(data);
                        weka.classifiers.Evaluation evalKnn = new weka.classifiers.Evaluation(data);
                        evalKnn.crossValidateModel(knn, data, 10, new Random(10));
                        
                        writer.write(i + "kNN" + System.getProperty("line.separator"));
                        writer.write(evalKnn.toClassDetailsString() + System.getProperty("line.separator"));
                    }
                }
                catch(Exception exc)
                {
                    System.out.println(exc.toString());
                }
            }
            writer.flush(); writer.close();
        }
        catch(Exception exc)
        {
            System.out.println(exc.toString());
        }
    }
}
