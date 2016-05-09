/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normalsolution;

import core.Literal;
import core.Rule;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author martin
 */
public class NormalSolver {

    private ArrayList<Rule> rules;

    public NormalSolver() {
        this.rules = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        this.getRules().add(rule);
    }

    /**
     * @return rules
     */
    public ArrayList<Rule> getRules() {
        return this.rules;
    }

    /**
     * @param rules the rules to set
     */
    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    public HashSet<Literal> findSmallestModel() {
        HashSet<Literal> res = new HashSet<>();
        
        int prevSize = res.size();
        while (true) {
            for (Rule r: this.rules) {
                if (r.isBodySatisfied(res)){
                    res.add(r.getHead());
                }
            }
            if (prevSize == res.size()) {
                return res;        
            } else {
                prevSize = res.size();
            }
        }
        
    }
}
