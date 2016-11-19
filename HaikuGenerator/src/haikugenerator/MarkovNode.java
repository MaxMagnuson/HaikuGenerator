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
public class MarkovNode {
    private Word name;
    private double probability = 0.0;
    private ArrayList<MarkovNode> nonZeroNodes;
    
    public MarkovNode(Word name)
    {
        this.name = name;
        this.probability++;
    }
    
    public void Normalize()
    {
        // Sum occurences of each word
        double sum = 0.0;
        for(int i = 0; i < this.nonZeroNodes.size(); i++)
        {
            sum += this.nonZeroNodes.get(i).GetProbability();
        }
        
        // Divide by the sum to normalize the probabilities
        for(int i = 0; i < this.nonZeroNodes.size(); i++)
        {
            this.nonZeroNodes.get(i).UpdateProbability(sum);
        }
    }
    
    public void IncrementFrequency()
    {
        this.probability++;
    }
    
    public void AddRelation(Word relatedWord)
    {
        //Todo: Finish method
    }
    
    public String GetName()
    {
        return this.name.GetWord();
    }
    
    public double GetProbability()
    {
        return this.probability;
    }
    
    private void UpdateProbability(double sum)
    {
        this.probability /= sum;
    }
    
}
