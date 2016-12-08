/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import haikugenerator.Interface.IGenerator;

/**
 *
 * @author MaxM
 */
public class HaikuGenerator {

    /**
     * @param args the command line arguments
     */
    //private static final String path = "C:\\Users\\MaxM\\Documents\\GitHub\\HaikuGenerator\\AesopLyrics.txt";
    private static final String path = "D:\\Documents\\Schoolwork\\AI\\HaikuGenerator\\AesopLyrics.txt";
    public static void main(String[] args) {
        // TODO code application logic here
        // Note: Feel Free to edit this with whatever test stuff
        
        Parser p = new Parser();
        MarkovChain mc = p.ParseFile(path, true);
        mc.PrintChain();
        
        IGenerator gen = new NaiveGenerator(mc);
        for(int i = 0; i < 0; i++)
        {
            System.out.println("Running iteration " + i);
            System.out.println(gen.GenerateHaiku().toString());
        }
    }
    
}
