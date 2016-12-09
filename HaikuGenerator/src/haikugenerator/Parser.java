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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MaxM
 */
public class Parser implements IParser {
    
    public List<String> badPunc;
    public List<String> badWords;
    
    public Parser(){
        this.badPunc = Arrays.asList("\\.", "\\,", "\\?", "\\!", "\"", "\\:", "\\;");
        this.badWords = Arrays.asList("a", "the", "and", "for", "or", "is", "it", "in");
    }
    
    public MarkovChain ParseFile(String FileName){
        return ParseFile(FileName, false);
    }
    
    public MarkovChain ParseFile(String FileName, boolean useBadList)
    {   
        File f = new File(FileName);
        MarkovChain chain;
        Scanner scan;
        try{
            scan = new Scanner(f);
            chain = Parse(scan, useBadList);
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return new MarkovChain();
        }
        chain.Normalize();
        return chain;
    }
    
    public MarkovChain ParseFiles(Collection<String> files, boolean useBadList){
        Iterator<String> it = files.iterator();
        MarkovChain chain = new MarkovChain();
        while(it.hasNext()){
            String FileName = it.next();
            File f = new File(FileName);
            Scanner scan;
            try{
                scan = new Scanner(f);
                chain = Parse(scan, chain, useBadList);
                scan.close();
            } catch (FileNotFoundException e){
                System.err.println("Could not find file: " + FileName);
                e.printStackTrace();
                continue;
            }
        }
        chain.Normalize();
        return chain;
    }
    
    private MarkovChain Parse(Scanner scan, boolean useBadList){
        return Parse(scan, null, useBadList);
    }
    
    private MarkovChain Parse(Scanner scan, MarkovChain chain, boolean useBadList){
        if(chain == null)
            chain = new MarkovChain();
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
                if(!badWords.contains(tokenWord.GetWord()) || !useBadList){ // If this is a bad word ignore it.
                    if(prevWord != null)
                        chain.UpdateChain(prevWord, tokenWord);
                    prevWord = tokenWord;
                }
                //token = token.replaceAll("\\.", "");
                
            }
            if(prevWord != null)
                chain.UpdateChain(prevWord);
        }
        return chain;
    }
}
