/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import haikugenerator.Haikus.FiveLine;
import haikugenerator.Haikus.IHaikuLine;
import haikugenerator.Haikus.SevenLine;
import haikugenerator.Interface.IGenerator;

/**
 *
 * @author MaxM
 */
public class NaiveLineGenerator implements IGenerator {
    private MarkovChain chain;
    public NaiveLineGenerator(MarkovChain chain)
    {
        this.chain = chain;
    }
    
    @Override
    public Haiku GenerateHaiku() 
    {
        MarkovNode currentNode = this.chain.GetRandomNode();
        IHaikuLine lineOne = new FiveLine();
        IHaikuLine lineTwo = new SevenLine();
        IHaikuLine lineThree = new FiveLine();
        
        lineOne = this.FillInLine(lineOne);
        lineTwo = this.FillInLine(lineTwo);
        lineThree = this.FillInLine(lineThree);
        
        return new Haiku(lineOne, lineTwo, lineThree);
    }
    
    private IHaikuLine FillInLine(IHaikuLine line)
    {
        MarkovNode currentNode = this.chain.GetRandomNode();
        while(!line.Full())
        {
            line.AddWord(currentNode.GetName());
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
        return line;
    }
    
}
