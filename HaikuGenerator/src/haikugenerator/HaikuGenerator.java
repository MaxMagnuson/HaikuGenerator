/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import haikugenerator.Interface.IGenerator;
import java.util.ArrayList;

/**
 *
 * @author MaxM
 */
public class HaikuGenerator {

    /**
     * @param args the command line arguments
     */
    private static final String path = "C:\\Users\\MaxM\\Documents\\GitHub\\HaikuGenerator\\AesopLyrics.txt";
    private static final String pathTwo = "C:\\Users\\MaxM\\Documents\\GitHub\\HaikuGenerator\\ShakespeareUnabridged.txt";
    //private static final String path = "D:\\Documents\\Schoolwork\\AI\\HaikuGenerator\\AesopLyrics.txt";
    public static void main(String[] args) {
        // TODO code application logic here
        // Note: Feel Free to edit this with whatever test stuff
        
        ArrayList<String> files = new ArrayList<String>();
        files.add(path);
        files.add(pathTwo);
        
        Parser p = new Parser();
        MarkovChain mc = p.ParseFile(pathTwo, true);
        //mc.PrintChain();
        
        IGenerator gen = new NaiveGenerator(mc);
        ShortRandomWalk_Generator collGen = new ShortRandomWalk_Generator(mc);
        //for(int i = 0; i < 0; i++)
        //{
            //System.out.println("Running iteration " + i);
            ArrayList<Haiku> haikus = collGen.GenHaikuCollection();
            for(int j = 0; j < haikus.size(); j++)
            {
                System.out.println(haikus.get(j).toString());
            }
            //System.out.println(gen.GenerateHaiku().toString());
        //}
    }
    
}
