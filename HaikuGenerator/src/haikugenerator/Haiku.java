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
public class Haiku {
    private ArrayList<Word> lineOne;
    private ArrayList<Word> lineTwo;
    private ArrayList<Word> lineThree;
    
    public Haiku()
    {
        this.lineOne = new ArrayList<Word>();
        this.lineTwo = new ArrayList<Word>();
        this.lineThree = new ArrayList<Word>();
    }
    
    // Checks to see if a new word can be added
    // If it can't, it returns false
    public boolean AddWord(Word newWord)
    {
        return false;
    }
}
