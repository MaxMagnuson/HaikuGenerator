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
public class MarkovChain {
    private ArrayList<MarkovNode> chain;
    private Random random;
    
    public MarkovChain()
    {
        this.chain = new ArrayList<MarkovNode>();
        this.random = new Random();
    }
    
    public boolean Contains(String name)
    {
        for(int i = 0; i < this.chain.size(); i++)
        {
            MarkovNode currentNode = this.chain.get(i);
            if((currentNode.GetName().GetWord()).equals(name))
            {
                return true;
            }
        }
        return false;
    }
    
    /** Returns null if it does not contain the given word. */
    public MarkovNode GetNode(String name)
    {
        for(int i = 0; i < this.chain.size(); i++)
        {
            MarkovNode currentNode = this.chain.get(i);
            if(currentNode.GetName().GetWord().equals(name))
            {
                return currentNode;
            }
        }
        return null;
    }
    
    public MarkovNode GetRandomNode()
    {
        double randDouble = this.random.nextDouble();
        int index = (int)Math.floor(randDouble/(1.0/chain.size())) - 1;
        return this.chain.get(index);
    }
    
    public void UpdateChain(Word word, Word relatedWord)
    {
        if(!this.Contains(word.GetWord()))
        {
            MarkovNode newNode = new MarkovNode(word);
            newNode.AddRelation(relatedWord);
            this.chain.add(newNode);
        }
        else
        {
            this.GetNode(word.GetWord()).AddRelation(relatedWord);
        }
    }
    
    public void UpdateChain(Word word){
        if(!this.Contains(word.GetWord())){
            MarkovNode newNode = new MarkovNode(word);
            this.chain.add(newNode);
        }
    }
    
    public void Normalize(){
        for(int i= 0; i < chain.size(); i++)
            chain.get(i).Normalize();
    }
    
    public void PrintChain(){
        for(int i = 0; i < chain.size(); i++){
            chain.get(i).PrintNode();
        }
    }
}
