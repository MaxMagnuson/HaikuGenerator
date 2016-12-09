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
import java.util.ArrayList;

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
    
    public ArrayList<Haiku> GenHaikuCollection()
    {
        MarkovNode root = this.chain.GetRandomNode();
        
        ArrayList<Walk> fives = new ArrayList<Walk>();
        for(int i = 0; i < ITERATIONS; i++)
        {
            Walk nextWalk = GenWalk(root, new FiveLine());
            // Maintains a sorted ascending order by steps
            fives = InsertWalk(fives, nextWalk);
        }
        
        ArrayList<Walk> sevens = new ArrayList<Walk>();
        for(int i = 0; i < ITERATIONS; i++)
        {
            Walk nextWalk = GenWalk(root, new SevenLine());
            // Maintains a sorted ascending order by steps
            sevens = InsertWalk(sevens, nextWalk);
        }
        System.out.println("fives size: " + fives.size());
        System.out.println("sevens size: " + sevens.size());
        Haiku fewSteps = new Haiku(fives.get(0).GetLine(), sevens.get(0).GetLine(), fives.get(1).GetLine());
        Haiku midSteps = new Haiku(fives.get(fives.size()/2).GetLine(), sevens.get(sevens.size()/2).GetLine(), fives.get(fives.size()/2 + 1).GetLine());
        Haiku mostSteps = new Haiku(fives.get(fives.size() - 2).GetLine(), sevens.get(sevens.size()-1).GetLine(), fives.get(fives.size() - 1).GetLine());
        
        ArrayList<Haiku> result = new ArrayList<Haiku>();
        result.add(fewSteps);
        result.add(midSteps);
        result.add(mostSteps);
        
        return result;
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
    
    private ArrayList<Walk> InsertWalk(ArrayList<Walk> walks, Walk walk)
    {
        for(int i = 0; i < walks.size(); i++)
        {
            if(walk.GetSteps() < walks.get(i).GetSteps())
            {
                walks.add(i, walk);
                return walks;
            }
        }
        //Runs if walks size is zero
        walks.add(walk);
        return walks;
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
