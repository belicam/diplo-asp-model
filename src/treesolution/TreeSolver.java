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
        
        boolean changed = true;
        int lastChanged = 0;        
        int i = 0;
        int prevSize = this.smallestModel.size();
        while (changed || (lastChanged != i)) {
            changed = false;
            this.trees.get(i).fire(null, this.smallestModel);
            if (prevSize != this.smallestModel.size()) {
                changed = true;
                lastChanged = i;
                prevSize = this.smallestModel.size();
            }
            i++;
            i %= this.trees.size();
        }
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
