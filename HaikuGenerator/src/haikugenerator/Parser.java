/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import haikugenerator.Interface.IParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MaxM
 */
public class Parser implements IParser {
    
    public List<String> badPunc;
    
    public Parser(){
        this.badPunc = Arrays.asList("\\.", "\\,", "\\?", "\\!", "\"", "\\:", "\\;");
    }
    
    public MarkovChain ParseFile(String FileName)
    {   
        File f = new File(FileName);
        Scanner scan;
        try{
            scan = new Scanner(f);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return new MarkovChain();
        }
        MarkovChain chain = new MarkovChain();
        while(scan.hasNextLine()){
            // Do something
            String line = scan.nextLine();
            Scanner linescan = new Scanner(line);
            Word prevWord = null;
            while(linescan.hasNext()){
                // Do more things.
                String token = linescan.next();
                token = token.toLowerCase();
                for(int i = 0; i < badPunc.size(); i++){
                    token = token.replaceAll(badPunc.get(i), "");
                }
                Word tokenWord = new Word(token);
                if(prevWord != null)
                    chain.UpdateChain(prevWord, tokenWord);
                prevWord = tokenWord;
                //token = token.replaceAll("\\.", "");
                
            }
            if(prevWord != null)
                chain.UpdateChain(prevWord);
        }
        scan.close();
        chain.Normalize();
        return chain;
    }
}
