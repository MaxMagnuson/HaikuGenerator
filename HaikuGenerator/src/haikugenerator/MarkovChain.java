/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import java.util.HashMap;

/**
 *
 * @author MaxM
 */
public class MarkovChain {
    private HashMap<String, MarkovNode> chain;
    
    public MarkovChain()
    {
        this.chain = new HashMap<String, MarkovNode>();
    }
    
    public void UpdateChain(Word word, Word relatedWord)
    {
        //TODO: Finish Method
        /*if(!this.chain.containsKey(word.GetWord()))
        {
            MarkovNode newNode = new MarkovNode(word);
            this.chain.put(word.GetWord(), new MarkovNode(word));
        }
        else
        {
            this.chain.get(word.GetWord()).IncrementFrequency();
        }*/
    }
}
