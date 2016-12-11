/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import normalsolution.NormalSolver;
import core.Constant;
import core.Rule;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import treesolution.TreeSolver;

/**
 *
 * @author martin
 */
public class NajmensiModel {

    public static ArrayList<Rule> readRulesFromFile() {
        ArrayList<Rule> rules = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("rules.txt"))) {
            stream.forEach((String line) -> {
                if (!line.isEmpty()) {
                String[] splitted = line.split("<-", 2);
                Rule r = new Rule();
                rules.add(r);

                if (!splitted[0].isEmpty()) {
                    r.setHead(new Constant(splitted[0]));
                }
                if (!splitted[1].isEmpty()) {
                    String[] bodySplitted = splitted[1].split(",");
                    for (String bodyLit : bodySplitted) {
                        r.addToBody(new Constant(bodyLit));
                    }
                }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(NajmensiModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rules;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Rule> rules = readRulesFromFile();

        System.out.println("NajmensiModel.NormalSolver");
        NormalSolver p = new NormalSolver();
        p.setRules(rules);
        System.out.println(p.getRules());
        System.out.println(p.findSmallestModel());
        
        ////////////////////////////////////////////////
        System.out.println("NajmensiModel.TreeSolver");
        TreeSolver ts = new TreeSolver(rules);
//        System.out.println(ts.getTrees());
        System.out.println(ts.findSmallestModel());
    }

}
