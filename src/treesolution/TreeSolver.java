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
        this.rules.stream().map((r) -> r.compile(c)).forEach((compiled) -> {
            trees.add(compiled);
        });
    }

   
    public HashSet<Literal> findSmallestModel() {
        this.smallestModel.clear();
        
        boolean changed = true;
        int i = 0;
        int prevSize = this.smallestModel.size();
        while (changed) {
            changed = false;
            resolveNode(this.trees.get(i), i);
            if (prevSize != this.smallestModel.size()) {
                changed = true;
                prevSize = this.smallestModel.size();
            }
            i++;
            i %= this.trees.size();
        }
        return this.smallestModel;
    }

    private void resolveNode(NodeRule node, int index) {
        if (node != null) {
            if (node.getBodyNotSatisfiedCount() == 0) {
                if (node.getNodeLiteral() != null) {
//                    applyHeadLiteral(node.getNodeLiteral().getLiteral()); 
                    
                }
            }
        }
    }

//    private void applyHeadLiteral(Literal literal) {
//        if (this.smallestModel.contains(literal)) {
//            return;
//        }
//        this.smallestModel.add(literal);
//        for (int i = 0; i < this.trees.size(); i++) {
//            if (this.trees.get(i).getRule().getBody().contains(literal)) {
//                this.trees.get(i).setBodyNotSatisfiedCount(this.trees.get(i).getBodyNotSatisfiedCount() - 1);
//            }
//        }
//    }
    
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
