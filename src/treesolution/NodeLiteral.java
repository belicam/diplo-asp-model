package treesolution;

import core.Literal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author martin
 */
public class NodeLiteral extends Node{     
    private Literal literal;
    private NodeRule rule;
    
    private boolean derived = false;
    
    public NodeLiteral() {
    }
    
    public NodeLiteral(Literal literal) {
        this.literal = literal;
        this.rule = null;
    }
    
    public NodeLiteral(Literal literal, NodeRule rule) {
        this.literal = literal;
        this.rule = rule;
    }

    /**
     * @return the literal
     */
    public Literal getLiteral() {
        return literal;
    }

    /**
     * @param literal the literal to set
     */
    public void setLiteral(Literal literal) {
        this.literal = literal;
    }

    /**
     * @return the rule
     */
    public NodeRule getNodeRule() {
        return rule;
    }

    /**
     * @param rule the rule to set
     */
    public void setNodeRule(NodeRule rule) {
        this.rule = rule;
    }
    
    @Override
    public String toString() {
        return (this.rule == null ? "" : this.rule.toString() + " <= ") + "L(" + this.literal + ")";
    }

    /**
     * @return the derived
     */
    public boolean isDerived() {
        return derived;
    }

    /**
     * @param derived the derived to set
     */
    public void setDerived(boolean derived) {
        this.derived = derived;
    }
}
