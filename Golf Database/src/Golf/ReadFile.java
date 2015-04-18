/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Golf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
/**
 * ReadFile.java
 * A class used to read cities from a text file with comma separated 5 fields per line:
 *      field1 : Name
 *      field2 : Population
 *      field3 : Median income
 *      field4 : Percent native
 *      field1 : Percent advanced degree
 * <pre>
 *    Project: Cities Database
 *    Platform: jdk 1.6.0_14; NetBeans IDE 6.8; Windows 7
 *    Course:
 *    Hourse: 2 hours and 45 minutes
 *    Created on Apr 4, 2011, 1:45:39 PM
 *    Revised on 
 * </pre>
 *
 * @author:	nculevsk@edcc.edu
 * @version: 	%1% %2%
 * @see:     	java.io.FileReader
 * @see:     	java.io.BufferedReader
 */
public class ReadFile
{
    private FileReader inputFile;
    private BufferedReader input;
    private boolean fileExists;

    public ReadFile(String inFilename)
    {
        try
        {
            inputFile = new FileReader(inFilename);
            input = new BufferedReader(inputFile);
            fileExists = true;
        } 
        catch (FileNotFoundException ex)
        {
            System.out.print("File Open Error: " + ex.getMessage());
            fileExists = false;
        }

    }

    public String readRecord()
    {
        String line = "";
        try
        {
            line = input.readLine();
        }
        catch (IOException ex) {}

        return line;
    }

    public void close()
    {
        try
        {
            inputFile.close();
        }
        catch (IOException ex) {}
    }
}
