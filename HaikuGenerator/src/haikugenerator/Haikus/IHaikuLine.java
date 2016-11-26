/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator.Haikus;

import haikugenerator.Word;
import java.util.ArrayList;

/**
 *
 * @author MaxM
 */
public abstract class IHaikuLine {
    private ArrayList<Word> line = new ArrayList<>();
    private int totalSyllables = 0;
    protected int MaxSyllables;
    
    /** Checks to see if a new word can be added
        If it can't, it returns false */
    public boolean AddWord(Word word)
    {
        if(this.totalSyllables + word.GetSyllables() > this.MaxSyllables)
        {
            return false;
        }
        else
        {
            this.line.add(word);
            this.totalSyllables += word.GetSyllables();
            return true;
        }
    }
    
    public boolean Full()
    {
        return this.totalSyllables == this.MaxSyllables;
    }
    
    @Override
    public String toString()
    {
        String result = "";
        for(int i = 0; i < line.size() - 1; i++)
        {
            result += line.get(i).GetWord() + " ";
        }
        result += line.get(line.size()-1).GetWord();
        return result;
    }
}
