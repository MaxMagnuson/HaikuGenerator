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
 * @author zbookey
 */
public class DFSB_Generator implements IGenerator{
    
    private boolean DEBUG = false;
    MarkovChain chain;
    
    public DFSB_Generator(MarkovChain chain){
        this.chain = chain;
    }

    public Haiku GenerateHaiku() {
        return GenerateHaiku(0);
    }
    
    // Usage indicates how the haiku should be generated.
    // 0 means use a new start node for each line.
    // 1 means use the previous lines end node as the next's start node.
    //     If the previous end node can't generate a valid line use a random node for this line.
    // 2 means use one start node to generate the whole haiku. If the start node chosen can't generate
    //     the whole haiku start the process over with a new one. CURRENTLY UNIMPLEMENTED
    public Haiku GenerateHaiku(int usage){
        if(usage == 1){
            DFSB_Node start;
            DFSB_Node line1Node = null;
            while(line1Node == null){
                line1Node = GenerateLineNode(new DFSB_Node(chain.GetRandomNode()),5);
            }
            DFSB_Node line2Node = null;
            while(line2Node == null){
                if(line1Node.VisitedAll())
                    line2Node = GenerateLineNode(new DFSB_Node(chain.GetRandomNode()), 7);
                else{
                    line1Node.CreateNewNode(); // Adds a new node to line1Node. Need to delete this
                    start = new DFSB_Node(line1Node.GetChild().GetMarkovNode());
                    line1Node.RemoveChild(); // Remove the child from the list so we don't mess up line1
                    line2Node = GenerateLineNode(start, 7);
                }
            }
            DFSB_Node line3Node = null;
            while(line3Node == null){
                if(line2Node.VisitedAll())
                    line3Node = GenerateLineNode(new DFSB_Node(chain.GetRandomNode()), 5);
                else{
                    line2Node.CreateNewNode(); // Adds a new node to line1Node. Need to delete this
                    start = new DFSB_Node(line2Node.GetChild().GetMarkovNode());
                    line2Node.RemoveChild(); // Remove the child from the list so we don't mess up line1
                    line3Node = GenerateLineNode(start, 5);
                }
            }
            return new Haiku(GenerateFiveLine(line1Node), GenerateSevenLine(line2Node), GenerateFiveLine(line3Node));
        }
        else if (usage == 2){
            if(DEBUG)
                System.out.println("Case 2 is unimplemented, using default of 0.");
            return GenerateHaiku(0);
        }
        else {
            DFSB_Node line1Node = null;
            while(line1Node == null)
                line1Node = GenerateLineNode(new DFSB_Node(chain.GetRandomNode()),5);
            DFSB_Node line2Node = null;
            while(line2Node == null)
                line2Node = GenerateLineNode(new DFSB_Node(chain.GetRandomNode()),7);
            DFSB_Node line3Node = null;
            while(line3Node == null)
                line3Node = GenerateLineNode(new DFSB_Node(chain.GetRandomNode()),5);
            return new Haiku(GenerateFiveLine(line1Node), GenerateSevenLine(line2Node), GenerateFiveLine(line3Node));
        }
    }
    
    private DFSB_Node GenerateLineNode(DFSB_Node root, int syllables){
        //DFSB_Node root = new DFSB_Node(chain.GetRandomNode());
        DFSB_Node current = root;
        if(DEBUG)
            root.PrintfromNode();
        while(current.GetSyllables()!=syllables){
            if(!current.VisitedAll()){ // We haven't tried everything so generate a new child.
                current.CreateNewNode();
                if(current.GetChild().GetSyllables() <= syllables)
                    current = current.GetChild();
            } else if(current == root){ // We've visited all the possible candidates and we have no parent.
                if(DEBUG)
                    System.out.println("Couldn't generate a valid chain of "+ syllables + " syllables");
                return null; // If we can't complete the line with the given start node return null. Let the user handle it...
                //return GenerateFiveLineNode(new DFSB_Node(chain.GetRandomNode())); // Best to start over with a new random start node.
            } else{
                current = current.GetParent(); // We exhausted all possibilites at that node. Move back to the parent.
            }
            if(DEBUG)
                root.PrintfromNode();
        }
        if(DEBUG)
            System.out.println("Successfully generated valid chain of " + syllables+ " syllables");
        return current;
    }
    
    private FiveLine GenerateFiveLine(DFSB_Node node){
        FiveLine line = new FiveLine();
        DFSB_Node iter = node.GetRoot();
        while(true){
            line.AddWord(iter.GetWord());
            if(!iter.HasChild()) return line;
            iter = iter.GetChild();
        }
    }
    
    private SevenLine GenerateSevenLine(DFSB_Node node){
        SevenLine line = new SevenLine();
        DFSB_Node iter = node.GetRoot();
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
        
        public DFSB_Node GetRoot(){
            if(this.HasParent())
                return parent.GetRoot();
            else
                return this;
        }
        
        public DFSB_Node GetChild(){
            return child;
        }
        
        public DFSB_Node GetLast(){
            if(this.HasChild())
                return child.GetLast();
            else
                return this;
        }
        
        public boolean HasParent(){
            return parent != null;
        }
        
        public boolean HasChild(){
            return child != null;
        }
        
        public void RemoveParent(){
            parent = null;
        }
        
        public void RemoveChild(){
            child = null;
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
        
        public MarkovNode GetMarkovNode(){
            return mknode;
        }
        public void PrintfromNode(){
            System.out.print(mknode.GetName().GetWord() + " ");
            if(this.HasChild())
                child.PrintfromNode();
            else
                System.out.println();
        }
    }
}