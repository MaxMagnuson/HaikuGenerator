/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import java.util.ArrayList;

/**
 *
 * @author MaxM
 */
public class HaikuGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Note: Feel Free to edit this with whatever test stuff
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("unicorns", 3));
        words.add(new Word("of", 1));
        words.add(new Word("love", 1));
        
        words.add(new Word("what", 1));
        words.add(new Word("a", 1));
        words.add(new Word("great", 1));
        words.add(new Word("name", 1));
        words.add(new Word("it's", 1));
        words.add(new Word("so", 1));
        words.add(new Word("great", 1));
        
        words.add(new Word("something", 2));
        words.add(new Word("something", 2));
        words.add(new Word("yeah", 1));
        
        Haiku haiku = new Haiku();
        for(int i = 0; i < words.size(); i++)
        {
            haiku.AddWord(words.get(i));
        }
        
        System.out.println(haiku.toString());
        Parser p = new Parser();
        MarkovChain mc = p.ParseFile("/home/zbookey/Documents/AI/HaikuGenerator/SampleInput.txt");
        mc.PrintChain();
    }
    
}
