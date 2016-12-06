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
public class Relation {
    private Word name;
    private Double probability;
    
    public Relation(Word name)
    {
        this.name = name;
        this.probability = 1.0;
    }
    
    public Word GetName()
    {
        return this.name;
    }
    
    public Double GetProbability()
    {
        return this.probability;
    }
    
    public void IncrementFrequency()
    {
        this.probability++;
    }
    
    /** Divides probability by the given sum. */
    public void Normalize(Double sum)
    {
        this.probability /= sum;
    }
}
