/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;
import haikugenerator.MarkovChain;
import haikugenerator.MarkovNode;
import haikugenerator.Word;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 *
 * @author MaxM
 */

public class MarkovChainTests {
    
    @Test
    public void NullTestWord()
    {
        String name = "seven";
        Word seven = new Word(name);
        
        assertEquals(name, seven.GetWord());
    }
    
    @Test
    public void AddsNewNode()
    {
       MarkovChain chain = new MarkovChain();
       Word pink = new Word("pink");
       Word unicorn = new Word("unicorn");
       
       chain.UpdateChain(pink, unicorn);
       MarkovNode pinkNode = chain.GetNode(pink.GetWord());
       
       assertEquals(pink.GetWord(), pinkNode.GetName());
    }
    
    @Test
    public void AddSecondRelation()
    {
        MarkovChain chain = new MarkovChain();
       Word pink = new Word("pink");
       Word unicorn = new Word("unicorn");
       Word candy = new Word("candy");
       
       chain.UpdateChain(pink, unicorn);
       chain.UpdateChain(pink, candy);
       MarkovNode pinkNode = chain.GetNode(pink.GetWord());
       
       assertEquals(2, pinkNode.Relations());
    }
    
    @Test
    public void DoesNotAddNewNode()
    {
        MarkovChain chain = new MarkovChain();
       Word pink = new Word("pink");
       Word unicorn = new Word("unicorn");
       
       chain.UpdateChain(pink, unicorn);
       chain.UpdateChain(pink, unicorn);
       MarkovNode pinkNode = chain.GetNode(pink.GetWord());
       
       assertEquals(1, pinkNode.Relations());
    }
    
    @Test
    public void AddTwoNodes()
    {
        MarkovChain chain = new MarkovChain();
       Word pink = new Word("pink");
       Word unicorn = new Word("unicorn");
       Word color = new Word("color");
       
       chain.UpdateChain(pink, unicorn);
       chain.UpdateChain(color, pink);
       
       assertTrue(chain.Contains(pink.GetWord()));
       assertTrue(chain.Contains(color.GetWord()));
    }
}
