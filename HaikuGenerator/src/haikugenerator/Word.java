/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

/**
 *
 * @author MaxM
 */
public class Word {
    private String word;
    private int syllables;
    
    public Word(String word)
    {
        this.word = word;
        //TODO: determine syllable
        // Should probably add an interface for this if we
        // want to test different methods. I'm unsure on this.
    }
    
    public String GetWord()
    {
        return this.word;
    }
    
    public int GetSyllables()
    {
        return this.syllables;
    }
}
