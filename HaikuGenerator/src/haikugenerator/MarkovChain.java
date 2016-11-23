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
public class MarkovChain {
    private ArrayList<MarkovNode> chain;
    
    public MarkovChain()
    {
        this.chain = new ArrayList<MarkovNode>();
    }
    
    public boolean Contains(String name)
    {
        for(int i = 0; i < this.chain.size(); i++)
        {
            MarkovNode currentNode = this.chain.get(i);
            if((currentNode.GetName()).equals(name))
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
            if(currentNode.GetName().equals(name))
            {
                return currentNode;
            }
        }
        return null;
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
}
