/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import haikugenerator.Interface.IGenerator;

/**
 *
 * @author MaxM
 */
public class NaiveGenerator implements IGenerator {
    private MarkovChain chain;
    public NaiveGenerator(MarkovChain chain)
    {
        this.chain = chain;
    }
    
    @Override
    public Haiku GenerateHaiku() {
        Haiku haiku = new Haiku();
        MarkovNode currentNode = this.chain.GetRandomNode();
        
        while(!haiku.Full())
        {
            haiku.AddWord(currentNode.GetName());
            Word nextWord = currentNode.NextWord();
            if(nextWord == null)
            {
                currentNode = this.chain.GetRandomNode();
            }
            else
            {
                currentNode = this.chain.GetNode(nextWord.GetWord());
            }
        }
        
        return haiku;
    }
    
    
}
