/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haikugenerator;

import haikugenerator.Haikus.FiveLine;
import haikugenerator.Haikus.SevenLine;
import haikugenerator.Interface.IGenerator;
import java.util.ArrayList;

/**
 *
 * @author zbookey
 */
public class DFSB_Generator implements IGenerator{
    
    MarkovChain chain;
    
    public DFSB_Generator(MarkovChain chain){
        this.chain = chain;
    }

    public Haiku GenerateHaiku() {
        FiveLine line1 = GenerateFiveLine();
        SevenLine line2 = GenerateSevenLine();
        FiveLine line3 = GenerateFiveLine();
        return new Haiku(line1, line2, line3);
    }
    
    private FiveLine GenerateFiveLine(){
        DFSB_Node root = new DFSB_Node(chain.GetRandomNode());
        DFSB_Node current = root;
        while(current.GetSyllables()!=5){
            if(!current.VisitedAll()){ // We haven't tried everything so generate a new child.
                current.CreateNewNode();
                if(current.GetChild().GetSyllables() <= 5)
                    current = current.GetChild();
            } else if(current == root){ // We've visited all the possible candidates and we have no parent.
                return GenerateFiveLine(); // Best to start over with a new random start node.
            } else{
                current = current.GetParent(); // We exhausted all possibilites at that node. Move back to the parent.
            }
        }
        FiveLine line = new FiveLine();
        DFSB_Node iter = root;
        while(true){
            line.AddWord(iter.GetWord());
            if(!iter.HasChild()) return line;
            iter = iter.GetChild();
        }
    }
    
    private SevenLine GenerateSevenLine(){
        DFSB_Node root = new DFSB_Node(chain.GetRandomNode());
        DFSB_Node current = root;
        while(current.GetSyllables()!=7){
            if(!current.VisitedAll()){ // We haven't tried everything so generate a new child.
                current.CreateNewNode();
                if(current.GetChild().GetSyllables() <= 7)
                    current = current.GetChild();
            } else if(current == root){ // We've visited all the possible candidates and we have no parent.
                return GenerateSevenLine(); // Best to start over with a new random start node.
            } else{
                current = current.GetParent(); // We exhausted all possibilites at that node. Move back to the parent.
            }
        }
        SevenLine line = new SevenLine();
        DFSB_Node iter = root;
        while(true){
            line.AddWord(iter.GetWord());
            if(!iter.HasChild()) return line;
            iter = iter.GetChild();
        }
    }
    
    public class DFSB_Node{
        private MarkovNode mknode;
        private DFSB_Node parent;
        private DFSB_Node child = null;
        private ArrayList<MarkovNode> visited = new ArrayList<>();
        
        public DFSB_Node(MarkovNode mknode){
            this.mknode = mknode;
            parent = null;
        }
        
        public DFSB_Node(MarkovNode mknode, DFSB_Node parent){
            this.mknode = mknode;
            this.parent = parent;
        }
        
        public DFSB_Node GetParent(){
            return parent;
        }
        
        public DFSB_Node GetChild(){
            return child;
        }
        
        public boolean HasParent(){
            return parent != null;
        }
        
        public boolean HasChild(){
            return child != null;
        }
        
        public void CreateNewNode(){
            Word nextWord = mknode.NextWord();
            MarkovNode newnode = chain.GetNode(nextWord.GetWord());
            if(!visited.contains(newnode)){
                visited.add(newnode);
                child = new DFSB_Node(newnode, this);
            } else this.CreateNewNode();
        }
        
        public int GetSyllables(){
            if(parent != null)
                return mknode.GetName().GetSyllables()+parent.GetSyllables();
            else
                return mknode.GetName().GetSyllables();
        }
        
        public int NumberOfRelations(){
            return mknode.Relations();
        }
        
        public boolean VisitedAll(){
            return NumberOfRelations() == visited.size();
        }
        
        public Word GetWord(){
            return mknode.GetName();
        }
    }
}