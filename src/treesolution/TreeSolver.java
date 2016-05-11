/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treesolution;

import java.util.ArrayList;
import java.util.HashSet;
import core.Literal;
import core.Rule;
import java.util.function.Consumer;

/**
 *
 * @author martin
 */
public class TreeSolver {

    private ArrayList<Rule> rules = new ArrayList<>();
    private ArrayList<NodeRule> trees = new ArrayList<>();
    
    private Cache c = new Cache();

    private HashSet<Literal> smallestModel = new HashSet<>();

    public TreeSolver(ArrayList<Rule> rules) {
        this.rules = rules;

        generateRuleTree();
    }

    private void generateRuleTree() {
        this.rules.stream().forEach((Rule r) -> {
            trees.add(r.compile(c));
        });
    }

   
    public HashSet<Literal> findSmallestModel() {
        this.smallestModel.clear();
        
        this.trees.stream().forEach(r -> r.fire(null, this.smallestModel));

        return this.smallestModel;
    }
    
    /**
     * @return rules
     */
    public ArrayList<Rule> getRules() {
        return this.rules;
    }

    /**
     * @return rules
     */
    public ArrayList<NodeRule> getTrees() {
        return this.trees;
    }
}
