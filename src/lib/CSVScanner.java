/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author uafederez
 */
public class CSVScanner implements AutoCloseable {
    private ArrayList<String> tokens;        
    private Scanner istream;
    private String  fieldDelim;
    private String  stringDelim;
    
    private void tokenizeStream()
    {
        while(istream.hasNextLine())
        {
            String line = istream.nextLine();
            int start   = 0;
            
            while(start <= line.length())
            {
                int delimPos   = line.indexOf(fieldDelim, start);
                int quoteStart = line.indexOf(stringDelim, start);
                int quoteEnd   = line.indexOf(stringDelim, quoteStart + 1);

                //System.out.println(start + " " + delimPos + " " + quoteStart + ", " + quoteEnd);

                while( (quoteStart != -1 && quoteEnd != -1) && (quoteStart < delimPos && delimPos < quoteEnd) ) 
                    delimPos = line.indexOf(",", quoteEnd);
                
                delimPos = (delimPos != -1 ? delimPos : line.length());
                String token = line.substring(start, delimPos).replace(stringDelim, "");
                start = delimPos + 1;
                tokens.add(token);
            }
        }
    }
    
    public CSVScanner(File inputFile, 
                      String fieldDelimiter, 
                      String stringDelimiter ) 
            throws java.io.IOException
    {   
        this.istream     = new Scanner(inputFile);
        this.tokens      = new ArrayList<>();
        this.fieldDelim  = fieldDelimiter;
        this.stringDelim = stringDelimiter;
        
        tokenizeStream();
    }
    
    public CSVScanner(File inputFile) 
            throws java.io.IOException
    {
        this(inputFile, ",", "\"");
    }
    
    public CSVScanner(String path) 
            throws java.io.IOException
    {
        this(new File(path), ",", "\"");
    }
    
    @Override
    public void close()
    {
        istream.close();
    }
    
    public boolean hasNext()
    {
        return !tokens.isEmpty();
    }
    
    public String next()
    {
        if(tokens.isEmpty())
            throw new java.util.NoSuchElementException();
        return tokens.remove(0);
    }
    
    public int nextInt()
    {
        if(tokens.isEmpty())
            throw new java.util.NoSuchElementException();
        return Integer.parseInt(tokens.remove(0));
    }
    
    public double nextDouble()
    {
        if(tokens.isEmpty())
            throw new java.util.NoSuchElementException();
        return Double.parseDouble(tokens.remove(0));
    }
}
