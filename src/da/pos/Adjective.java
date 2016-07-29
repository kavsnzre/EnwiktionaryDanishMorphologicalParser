/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.lcl.wimmp.da.pos;

import it.uniroma1.lcl.wimmp.da.Constants;
import it.uniroma1.lcl.wimmp.da.DanishMorphoRuleAdjective;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author michele
 */
public class Adjective {

    private final String lemma;
    private String template;

    public Adjective(String lemma, String template) {
        this.lemma = lemma;
        this.template = template;
    }

    public DanishMorphoRuleAdjective createAllFlections() {
        String singularNeuter = null;
        String singularDefiniteAndPlural = null;
        String comparative = null;
        String superlative = null;

        if (isRegularAdjectiveTemplate()) {
            Constants.allEntriesCount++;
            Constants.consideredEntriesCount++;
            if (isNotFlexible()) {
                System.out.println(template);
                // We have just do nothing
            } else if (template.matches("\\{\\{head\\|da\\|adjective(\\|((g(2)?=(n|c))|(neuter|neuter singular|definite and plural|definite or plural|definite singular and plural|plural and definite|comparative|superlative|or)\\|[^\\\\|=]*))+\\}\\}")) {
                template = template.replaceAll("\\[|\\]", "");
                System.out.println(template);
                template = template.replaceAll("(\\{\\{head\\|da\\|adjective)|(\\}\\})", "");
                Scanner scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                String field;
                String lastLabel = null;
                Scanner scanLabeledField;
                HashMap<String, String> fields = new HashMap<>();
                while (scanTemplate.hasNext()) {
                    field = scanTemplate.next();
                    if (field.matches(".*=.*")) {
                        // is a labeled field
                        scanLabeledField = new Scanner(field);
                        scanLabeledField.useDelimiter("=");
                        fields.put(scanLabeledField.next(), scanLabeledField.next());
                        // e.g. 
                        // g=c
                        // first time scanLabeledField.next() returns "g"
                        // second time scanLabeledField.next() returns "c"
                    } else if (field.equals("or")) {
                        fields.put(lastLabel, fields.get(lastLabel) + "/" + scanTemplate.next());
                        // e.g.
                        // |neuter|bundent|or|bundet
                        // in fields we'll have (K,V)=("singular neuter", "bundent/bundet"
                    } else {
                        if (field.matches("neuter|neuter singular")) {
                            lastLabel = "singular neuter";
                        } else if (field.matches("definite and plural|definite or plural|definite singular and plural|plural and definite")) {
                            lastLabel = "singular definite and plural";
                        } else if (field.matches("comparative")) {
                            lastLabel = "comparative";
                        } else if (field.equals("superlative")) {
                            lastLabel = "superlative";
                        }
                        fields.put(lastLabel, scanTemplate.next());
                    }
                }

                // singular neuter 
                if (fields.containsKey("singular neuter")) {
                    singularNeuter = fields.get("singular neuter");
                }
                // singular definite and plural
                if (fields.containsKey("singular definite and plural")) {
                    singularDefiniteAndPlural = fields.get("singular definite and plural");
                }
                // comparative
                if (fields.containsKey("comparative")) {
                    comparative = fields.get("comparative");
                }
                // superlative
                if (fields.containsKey("superlative")) {
                    superlative = fields.get("superlative");
                }
            } else if (template.matches("\\{\\{da\\-adj(\\|[A-Za-zøåæó ]+){2,4}\\}\\}")) {

                System.out.println(template);
                template = template.replaceAll("(\\{\\{da\\-adj)|(\\}\\})", "");
                Scanner scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                if (scanTemplate.hasNext()) {
                    singularNeuter = scanTemplate.next();
                }
                if (scanTemplate.hasNext()) {
                    singularDefiniteAndPlural = scanTemplate.next();
                }
                if (scanTemplate.hasNext()) {
                    comparative = scanTemplate.next();
                    if (comparative.equals("mere")) {
                        comparative += " " + lemma;
                    }
                }
                if (scanTemplate.hasNext()) {
                    superlative = scanTemplate.next();
                    if (superlative.equals("mest")) {
                        superlative += " " + lemma;
                    }
                }
            } else {
                // other 36 not considered irregular cases
                if (template.startsWith("{{da-adj")
                        || template.startsWith("{{head|da|adjective")) {
                    System.out.println("NOT COVERED ADJECTIVE: " + template);
                    Constants.consideredEntriesCount--;
                } else {
                    Constants.allEntriesCount--;
                    Constants.consideredEntriesCount--;
                }
                return null;
            }
        } else if (!isRegularAdjectiveTemplate()) {
            // Not a good entry, to ignore!
            return null;
        }
        return new DanishMorphoRuleAdjective(
                singularNeuter,
                singularDefiniteAndPlural,
                comparative,
                superlative
        );
    }

    private boolean isRegularAdjectiveTemplate() {
        return !(template.startsWith("{{head|da|adjective form")
                || template.equals("{{head|da|adjective comparative form}}")
                || template.equals("{{head|da|adjective superlative form}}")
                || template.equals("{{head|da|adjective}}")
                || template.equals("{{head|da|adjectives}}")
                || template.equals("{{da-adj}}")
                || template.endsWith("comparative form}}"));

    }

    private boolean isNotFlexible() {
        return template.matches("\\{\\{head\\|da\\|adjective\\|(indeclinable|inflexible| not inflected)\\}\\}( \\(\\'\\'(indeclinable|uninflected)\\'\\'\\))?")
                || template.equals("{{head|da|adjective}} (''uninflected'')")
                || template.equals("{{head|da|adjective}} (''indeclinable'')");
    }
}
