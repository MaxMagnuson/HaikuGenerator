/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author MaxM
 */
public class Word {
    private String word;
    private int syllables;
    private final List<Character> vowels;
    
    public Word(String word)
    {
        this.vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
        this.word = word;
        this.determineSyllables(this.word.toLowerCase());
        //TODO: determine syllable
        // Should probably add an interface for this if we
        // want to test different methods. I'm unsure on this.
    }
    
    public Word(String word, int syllables)
    {
        this.vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
        this.word = word;
        this.syllables = syllables;
        //TODO: this will probably be deprecated
    }
    
    public String GetWord()
    {
        return this.word;
    }
    
    public int GetSyllables()
    {
        return this.syllables;
    }
    
    /** Uses syllables logic according to source: 
     * http://www.phonicsontheweb.com/syllables.php */
    private void determineSyllables(String word)
    {
        
        //Todo: Add logic for tripthongs
        int syllables = 0;
        if(this.vowels.contains(word.charAt(0)))
        {
            syllables++;
        }
        for(int i = 1; i < word.length()-1; i++)
        {
            char currentChar = word.charAt(i);
            if(this.vowels.contains(currentChar))
            {
                if(!this.vowels.contains(word.charAt(i-1)))
                {
                    syllables++;
                }
            }
        }
        char lastChar = word.charAt(word.length()-1);
        if(lastChar=='e')
        {
            if(word.charAt(word.length()-2)=='l')
            {
                syllables++;
            }
            else if(!this.vowels.contains(word.charAt(word.length()-3)))
            {
                syllables++;
            }
        }
        else if(lastChar=='y')
        {
            if(!this.vowels.contains(word.charAt(word.length()-2)))
            {
                syllables++;
            }
        }
        this.syllables = syllables;
    }
    
    /**
     This will probably be deprecated.
     */
    public void SetSyllables(int syllables)
    {
        this.syllables = syllables;
    }
}
