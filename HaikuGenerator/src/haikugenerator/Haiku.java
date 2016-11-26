/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import haikugenerator.Haikus.FiveLine;
import haikugenerator.Haikus.IHaikuLine;
import haikugenerator.Haikus.SevenLine;
import java.util.ArrayList;

/**
 *
 * @author MaxM
 */
public class Haiku {
    private ArrayList<IHaikuLine> lines;
    
    public Haiku()
    {
        this.lines = new ArrayList<>();
        this.lines.add(new FiveLine());
        this.lines.add(new SevenLine());
        this.lines.add(new FiveLine());
    }
    
    // Checks to see if a new word can be added
    // If it can't, it returns false
    public boolean AddWord(Word newWord)
    {
        boolean added;
        for(int i = 0; i < this.lines.size(); i++)
        {
            added = this.lines.get(i).AddWord(newWord);
            if(added)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean Full()
    {
        for(int i = 0; i < lines.size(); i++)
        {
            if(!this.lines.get(i).Full())
            {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        String output = "";
        for(int i = 0; i < this.lines.size(); i++)
        {
            output += this.lines.get(i).toString() + "\n";
        }
        return output;
    }
}
