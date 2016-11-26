/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author MaxM
 */
public class MarkovNode {
    private Word name;
    private ArrayList<Relation> relations;
    private Random rand;
    
    public MarkovNode(Word name)
    {
        this.relations = new ArrayList<Relation>();
        this.name = name;
        this.rand = new Random();
    }
    
    /** Sums occurences of each word.
     Then, divides by the sum to normalize the probabilities. */
    public void Normalize()
    {
        double sum = 0.0;
        for(int i = 0; i < this.relations.size(); i++)
        {
            sum += this.relations.get(i).GetProbability();
        }
        
        for(int i = 0; i < this.relations.size(); i++)
        {
            this.relations.get(i).Normalize(sum);
        }
    }
    
    /** If the relation already exists, then its probability is incremented.
     Otherwise, a new relation is added. */
    public void AddRelation(Word relatedWord)
    {
        Relation exists = GetRelation(relatedWord);
        if(exists == null)
        {
            this.relations.add(new Relation(relatedWord.GetWord()));
        }
        else
        {
            exists.IncrementFrequency();
        }
    }
    
    private Relation GetRelation(Word relatedWord)
    {
        for(int i = 0; i<this.relations.size(); i++)
        {
            Relation currentRelation = this.relations.get(i);
            if(currentRelation.GetName().equals(relatedWord.GetWord()))
            {
                return currentRelation;
            }
        }
        return null;
    }
    
    /** Randomly chooses a related word and returns it as a string. */
    public String NextWord()
    {
        double probability = this.rand.nextDouble();
        double total = 0;
        Relation current = null;
        for(int i = 0; i<this.relations.size(); i++)
        {
            current = this.relations.get(i);
            total += current.GetProbability();
            if(total >= probability)
            {
                return current.GetName();
            }
        }
        return current.GetName();
    }
    
    public int Relations()
    {
        return this.relations.size();
    }
    
    public String GetName()
    {
        return this.name.GetWord();
    }
}
