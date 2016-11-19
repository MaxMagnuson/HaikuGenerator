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
public class Generator implements IGenerator {
    private MarkovChain chain;
    
    public Generator(MarkovChain chain)
    {
        this.chain = chain;
    }
    
    public Haiku GenerateHaiku()
    {
        //TODO: Actually generate haiku
        return new Haiku();
    }
}
