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
public class ShortRandomWalk_Generator implements IGenerator {
    
    private MarkovChain chain;
    public ShortRandomWalk_Generator(MarkovChain chain)
    {
        this.chain = chain;
    }
    
    private int ITERATIONS = 10;
    @Override
    public Haiku GenerateHaiku() 
    {
        MarkovNode root = chain.GetRandomNode();
        
        //Make both five lines
        Walk firstLine = GenWalk(root, new FiveLine());
        Walk thirdLine = GenWalk(root, new FiveLine());
        //Swap if two took fewer steps than one
        if(firstLine.GetSteps() > thirdLine.GetSteps())
        {
            SwapWalks(firstLine, thirdLine);
        }
        
        for(int i = 0; i < ITERATIONS; i++)
        {
            Walk nextWalk = GenWalk(root, new FiveLine());
            if(nextWalk.steps < firstLine.GetSteps())
            {
                SwapWalks(firstLine, thirdLine);
                firstLine = nextWalk;
            }
            else if(nextWalk.steps < thirdLine.GetSteps())
            {
                thirdLine = nextWalk;
            }
        }
        
        Walk secondLine = GenWalk(root, new SevenLine());
        for(int i = 0; i < ITERATIONS; i++)
        {
            Walk nextWalk = GenWalk(root, new SevenLine());
            if(nextWalk.GetSteps() < secondLine.GetSteps())
            {
                secondLine = nextWalk;
            }
        }
        
        return new Haiku(firstLine.GetLine(), secondLine.GetLine(), thirdLine.GetLine());
    }
    
    private Walk GenWalk(MarkovNode root, IHaikuLine line)
    {
        MarkovNode tempRoot = root;
        int steps = 0;
        Word currentWord = root.NextWord();
        while(!line.Full())
        {
            line.AddWord(currentWord);
            steps++;
            MarkovNode currentNode = this.chain.GetNode(currentWord.GetWord());
            while(currentNode.Relations() == 0)
            {
                currentNode = this.chain.GetRandomNode();
            }
            currentWord = currentNode.NextWord();
        }
        return new Walk(line, steps);
    }
    
    private void SwapWalks(Walk one, Walk two)
    {
        Walk tempWalk = new Walk(one.GetLine(), one.GetSteps());
        one = two;
        two = tempWalk;
    }
    
    
    private class Walk
    {
        private IHaikuLine line;
        private int steps;
        
        public Walk(IHaikuLine line, int steps)
        {
            this.line = line;
            this.steps = steps;
        }
        
        public IHaikuLine GetLine()
        {
            return this.line;
        }
        
        public int GetSteps()
        {
            return this.steps;
        }
        
        public void UpdateWalk(IHaikuLine line, int steps)
        {
            this.line = line;
            this.steps = steps;
        }
    }
}
