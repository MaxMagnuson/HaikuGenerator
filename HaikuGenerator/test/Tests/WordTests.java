/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;
import haikugenerator.Word;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 *
 * @author MaxM
 */
public class WordTests {
    
    @Test
    public void CorrectSyllablesOne()
    {
        Word word = new Word("Unicorn");
        assertEquals(3, word.GetSyllables());
    }
    
    @Test
    public void CorrectSyllablesTwo()
    {
        Word word = new Word("ReAlLy");
        assertEquals(2, word.GetSyllables());
    }
    
    @Test
    public void CorrectSyllablesThree()
    {
        Word word = new Word("cackle");
        assertEquals(2, word.GetSyllables());
    }
    
    @Test
    public void CorrectSyllablesFour()
    {
        Word word = new Word("dismay");
        assertEquals(2, word.GetSyllables());
    }
    
    @Test
    public void CorrectSyllablesFive()
    {
        Word word = new Word("bye");
        assertEquals(1, word.GetSyllables());
    }
    
    @Test
    public void CorrectSyllablesSix()
    {
        Word word = new Word("queueing");
        assertEquals(2, word.GetSyllables());
    }
    
    @Test
    public void CorrectSyllablesSeven()
    {
        Word word = new Word("geology");
        assertEquals(4, word.GetSyllables());
    }
}
