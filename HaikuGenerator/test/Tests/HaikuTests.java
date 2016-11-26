/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import haikugenerator.Haiku;
import haikugenerator.Word;
import java.util.ArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author MaxM
 */
public class HaikuTests {
    
    @Test
    public void CanAddWords()
    {
        Word word = new Word("Unicorn");
        Haiku haiku = new Haiku();
        
        haiku.AddWord(word);
        
        assertNotNull(haiku);
        assertFalse(haiku.Full());
    }
    
    @Test
    public void FullHaiku()
    {
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
        
        assertTrue(haiku.Full());
    }
}
