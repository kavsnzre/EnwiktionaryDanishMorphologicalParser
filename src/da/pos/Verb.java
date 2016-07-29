/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.lcl.wimmp.da.pos;

import it.uniroma1.lcl.wimmp.da.Constants;
import it.uniroma1.lcl.wimmp.da.DanishMorphoRuleVerb;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author michele
 */
public class Verb {

    private final String lemma;
    private String template;

    public Verb(String lemma, String template) {
        this.lemma = lemma;
        this.template = template;
    }

    public DanishMorphoRuleVerb createAllFlections() {
        String infinitive = lemma;
        String imperative = null;
        String pastParticiple = null;
        String presentParticiple = null;
        String presentTense = null;
        String pastTense = null;
        String futureTense = null;
        String presentPerfect = null;
        String pastPerfect = null;
        String futurePerfect = null;
        String presentConditional = null;
        String pastConditional = null;
        String passiveInfinitive = null;
        String passiveImperative = null;
        String passivePastParticiple = null;
        String passivePresentParticiple = null;
        String passivePresentTense = null;
        String passivePastTense = null;
        String passiveFutureTense = null;
        String passivePresentPerfect = null;
        String passivePastPerfect = null;
        String passiveFuturePerfect = null;
        String passivePresentConditional = null;
        String passivePastConditional = null;

        String other = "";
        String auxiliarVerb = null;
        if (isRegularVerbTemplate()) {
            Constants.allEntriesCount++;
            Constants.consideredEntriesCount++;
            Scanner scanTemplate;
            if (template.startsWith("{{da-conj-base")) {
                System.out.println(template);
                template = template.replaceAll("\\{\\{da-conj-base|\\}\\}", "");
                scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                //{{da-conj-base|present tense|past tense|imperative|present participle|past participle|passive present tense|passive past tense|passive present participle|passibe past participle}}
                // extracting the 9 infos from template
                presentTense = scanTemplate.next();
                pastTense = scanTemplate.next();
                imperative = scanTemplate.next();
                presentParticiple = scanTemplate.next();
                pastParticiple = scanTemplate.next();
                passivePresentTense = scanTemplate.next() + "/bliver " + pastParticiple;
                passivePastTense = scanTemplate.next() + "/blev " + pastParticiple;
                passivePresentParticiple = scanTemplate.next() + "/blivende " + pastParticiple;
                passivePastParticiple = scanTemplate.next() + "/var " + pastParticiple;

                // deriving the other inflections
                futureTense = "vil " + infinitive;
                presentConditional = "ville " + infinitive;

                presentPerfect = "har " + pastParticiple;
                pastPerfect = "havde " + pastParticiple;
                futurePerfect = "vil have " + pastParticiple;
                pastConditional = "ville have " + pastParticiple;
                passiveInfinitive = infinitive + "s/blive " + pastParticiple;
                passiveImperative = "bliv " + pastParticiple;
                passiveFutureTense = "vil blive " + pastParticiple;
                passivePresentPerfect = "er blevet " + pastParticiple;
                passivePastPerfect = "var blevet " + pastParticiple;
                passiveFuturePerfect = "vill være " + pastParticiple;
                passivePresentConditional = "ville blive " + pastParticiple;
                passivePastConditional = "ville være " + pastParticiple;

            } else if (template.startsWith("{{da-conj-reg")) {
                System.out.println(template);
                template = template.replaceAll("\\{\\{da-conj-reg|\\}\\}", "");
                scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                //{{da-conj-reg|imperative}}
                // infinitive "at" + lemma
                // imperative 1
                // past participle "lemma"+"t"
                // present participle "lemma"+"(e)nde"
                // present tense (present tense) "lemma" + "r"
                // past tense (past tense) "lemma" + "de"
                // future tense "vil" + lemma
                // present perfect "har" + "lemma"+"t"
                // past perfect "havde" + "lemma"+"t"
                // future perfect "vil have" + "lemma"+"t"
                // conditial present "ville" + lemma
                // conditional past "ville have" + "lemma"+"t"
                imperative = scanTemplate.next();
                pastParticiple = lemma + "t";
                if (lemma.endsWith("e")) {
                    presentParticiple = lemma + "nde";
                } else {
                    presentParticiple = lemma + "ende";
                }
                presentTense = lemma + "r";
                pastTense = lemma + "de";
                futureTense = "vil " + infinitive;
                presentPerfect = "har " + pastParticiple;
                pastPerfect = "havde " + pastParticiple;
                futurePerfect = "vil have " + pastParticiple;
                presentConditional = "ville " + infinitive;
                pastConditional = "ville have " + pastParticiple;

                passivePresentTense = presentTense + "s/bliver " + pastParticiple;
                passivePastTense = pastTense + "s/blev " + pastParticiple;
                passivePresentParticiple = presentParticiple + "s/blivende " + pastParticiple;
                passivePastParticiple = pastParticiple + "s/var " + pastParticiple;
                passiveInfinitive = infinitive + "s/blive " + pastParticiple;
                passiveImperative = "bliv " + pastParticiple;
                passiveFutureTense = "vil blive " + pastParticiple;
                passivePresentPerfect = "er blevet " + pastParticiple;
                passivePastPerfect = "var blevet " + pastParticiple;
                passiveFuturePerfect = "vill være " + pastParticiple;
                passivePresentConditional = "ville blive " + pastParticiple;
                passivePastConditional = "ville være " + pastParticiple;

            } else if (template.matches("\\{\\{head\\|da\\|verb(\\|("
                    + "imperative|"
                    + "infinitive|"
                    + "present( tense)?|"
                    + "past( tense)?|"
                    + "past participle( \\{\\{g\\|n\\}\\})?|"
                    + "present participle|"
                    + "or|&amp;#32;|&#32;|"
                    + "abbreviation|"
                    + "(\\{\\{g\\|(n|c|p)\\}\\}))"
                    + "\\|[^\\| \\{=]*)+"
                    + "\\}\\}")) {
                System.out.println(template);
                template = template.replaceAll("(\\{\\{head\\|da\\|verb)|(\\}\\})", "");
                scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                // e.g.
                // {{head|da|verb|imperative|frys|present|fryser|past|frøs|past participle|frosset|&amp;#32;|frossen|or|frosne}}
                // {{head|da|verb|imperative|drik|present|drikker|past|drak|past participle||{{g|n}}|drukket|{{g|c}}|drukken|{{g|p}}|drukne}}
                // {{head|da|verb|imperative|brows|present|browser|past|browsede|past participle|browset}}
                // {{head|da|verb|imperative|grib|present|griber|past|greb|past participle {{g|n}}|grebet|{{g|c}}|greben|{{g|p}}|grebne|}}
                // We'll only consider neuter singular version of past participle (the only to be in the most of cases)
                // Schema: {{imperative|present tense|past tense|past participle}}
                // We don't know auxiliar verb!
                String label, value;
                String lastLabel = null;
                Scanner scanLabeledField;
                HashMap<String, String> fields = new HashMap<>();
                while (scanTemplate.hasNext()) {
                    label = scanTemplate.next();
                    if (label.matches(".*=.*")) {
                        // is a labeled field
                        scanLabeledField = new Scanner(label);
                        scanLabeledField.useDelimiter("=");
                        fields.put(scanLabeledField.next(), scanLabeledField.next());
                        // e.g. 
                        // stem=kropp
                        // first time scanLabeledField.next() returns "stem"
                        // second time scanLabeledField.next() returns "kropp"
                    } else if (label.matches("or|&amp;#32;|&#32;")) {
                        // fields.put(lastLabel, fields.get(lastLabel) + "/" + scanTemplate.next());
                        scanTemplate.next();
                        // e.g.
                        // |plural indefinite|hundreder|or|hundred
                        // in fields we'll have (K,V)=("plural indefinite", "hundreder/hundred"
                    } else if (label.matches("imperative")) {
                        label = "imperative";
                        lastLabel = label;
                        value = scanTemplate.next();
                        fields.put(label, value);
                    } else if (label.matches("present( tense)?")) {
                        label = "present tense";
                        lastLabel = label;
                        value = scanTemplate.next();
                        fields.put(label, value);
                    } else if (label.equals("past( tense)?")) {
                        label = "past tense";
                        lastLabel = label;
                        value = scanTemplate.next();
                        fields.put(label, value);
                    } else if (label.matches("past participle( \\{\\{g\\|n\\}\\})?")) {
                        if (label.equals("past participle")) {
                            label = "past participle";
                            lastLabel = label;
                            value = scanTemplate.next();
                            if (value.equals("")) {
                                scanTemplate.next(); // ignoring "{{g|n}}"
                                value = scanTemplate.next();
                            }
                            // e.g. 
                            // past participle||{{g|n}}|drukket|{{g|c}}|drukken|{{g|p}}|drukne}}
                            // becomes (label,value)=(past participle,drukket)
                            // conversely
                            // |past participle|afskyet
                            // becomes (label,value)=(past participle,afskyet)
                        } else {
                            value = scanTemplate.next();
                            // e.g. 
                            // past participle {{g|n}}|grebet|{{g|c}}|greben|{{g|p}}|grebne|}}
                            // becomes (label,value)=(past participle,grebet)
                        }
                        fields.put(lastLabel, value);
                    }
                }

                if (fields.containsKey("imperative")) {
                    imperative = fields.get("imperative");
                } else if (fields.containsKey("present tense")) {
                    presentTense = fields.get("present tense");
                } else if (fields.containsKey("past tense")) {
                    pastTense = fields.get("past tense");
                } else if (fields.containsKey("past participle")) {
                    pastParticiple = fields.get("past participle");
                }

                // deriving the other inflections
                infinitive = lemma;
                presentParticiple = infinitive + (infinitive.endsWith("e") ? "nde" : "ende");
                futureTense = "vil " + infinitive;
                presentConditional = "ville " + infinitive;

                // we don't know the auxiliar verb(s) associated to the verb!
            } else if (template.matches("\\{\\{da-verb(\\|[^\\|=]*){6}(\\}\\})")) {
                System.out.println(template);
                template = template.replaceAll("\\{\\{da-verb|\\}\\}", "");
                scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                //{{da-verb|imperative|infinitive|present tense|past tense|har/er|past participle}}
                // extracting the 6 infos from template
                // ---1---
                imperative = scanTemplate.next();
                if (imperative.equals("-")) {
                    imperative = infinitive.substring(0, infinitive.length() - 2);
                    // Only two cases in enWiktionary:
                    // skulle -> skul
                    // kunne -> kun 
                    // Source: Danish Verb Conjugator (online service)
                } else if (imperative.matches("\\[\\[.*\\]\\] or \\[\\[.*\\]\\]")) {
                    imperative = imperative.replaceAll("(\\[\\[.*\\]\\] or \\[\\[)|(\\]\\])", "");
                    // Only two cases in enWiktionary:
                    // [[dividér]] or [[divider]] -> divider
                    // [[subtrahér]] or [[subtraher]] -> subtraher
                } else if (imperative.contains(" ")) {
                    other = imperative.replaceAll((new Scanner(imperative)).next(), "");
                    scanTemplate = new Scanner(template.replaceAll(other, ""));
                    scanTemplate.useDelimiter("\\|");
                    imperative = scanTemplate.next();
                    // e.g.
                    // {{da-verb|ånd ud|ånde ud|ånder ud|åndede ud|har|åndet ud}}
                    // ---> {{da-verb|ånd|ånde|ånder|åndede|har|åndet}}
                }
                // ---2---
                infinitive = scanTemplate.next();
                if (infinitive.contains(" ")) {
                    other = infinitive.replaceAll((new Scanner(infinitive)).next(), "");
                    scanTemplate = new Scanner(template.replaceAll(other, ""));
                    scanTemplate.useDelimiter("\\|");
                    scanTemplate.next(); // skip imperative
                    infinitive = scanTemplate.next();
                    // e.g.
                    // {{da-verb|-|kunne lide|kan lide|kunne lide|har|kunnet lide}}
                    // ---> {{da-verb|-|kunne|kan|kunne|har|kunnet}}
                }
                // ---3---
                presentTense = scanTemplate.next();
                if (presentTense.contains(" ")) {
                    presentTense = (new Scanner(presentTense)).next();
                }
                // ---4---
                pastTense = scanTemplate.next();
                if (pastTense.contains(" ")) {
                    pastTense = (new Scanner(pastTense)).next();
                }
                // ---5---
                String auxiliar = scanTemplate.next();
                if (auxiliar.equals("") || auxiliar.matches("((\\[\\[)?har(\\]\\])?/(\\[\\[)?er(\\]\\])?|er/har|er/hat|hat/er)")) {
                    auxiliarVerb = "both";
                } else if (auxiliar.matches("har|hat")) {
                    auxiliarVerb = "have";
                } else if (auxiliar.matches("er")) {
                    auxiliarVerb = "være";
                }
                // ---6---
                pastParticiple = scanTemplate.next();

                // deriving the other inflections
                presentParticiple = infinitive + (infinitive.endsWith("e") ? "nde" : "ende");
                futureTense = "vil " + infinitive;
                presentConditional = "ville " + infinitive;

                if (auxiliarVerb.matches("both")) {
                    presentPerfect = "har " + pastParticiple + other;
                    pastPerfect = "havde " + pastParticiple + other;
                    futurePerfect = "vil have " + pastParticiple + other;
                    pastConditional = "ville have " + pastParticiple + other;

                    passiveInfinitive = infinitive + "s/blive " + pastParticiple + other;
                    passivePastParticiple = pastParticiple + "s/var " + pastParticiple + other;
                    passivePresentParticiple = presentParticiple + "s/blivende " + pastParticiple + other;
                    passiveImperative = "bliv " + pastParticiple + other;
                    passivePresentTense = infinitive + "s/bliver " + pastParticiple + other;
                    passivePastTense = pastTense + "s/blev " + pastParticiple + other;
                    passiveFutureTense = "vil blive " + pastParticiple + other;
                    passivePresentPerfect = "er blevet " + pastParticiple + other;
                    passivePastPerfect = "var blevet " + pastParticiple + other;
                    passiveFuturePerfect = "vill være " + pastParticiple + other;
                    passivePresentConditional = "ville blive " + pastParticiple + other;
                    passivePastConditional = "ville være " + pastParticiple + other;
                } else if (auxiliarVerb.matches("være")) {
                    presentPerfect = "er " + pastParticiple + other;
                    pastPerfect = "var " + pastParticiple + other;
                    futurePerfect = "vil være " + pastParticiple + other;
                    pastConditional = "ville være " + pastParticiple + other;

                } else if (auxiliarVerb.matches("have")) {
                    presentPerfect = "har " + pastParticiple + other;
                    pastPerfect = "havde " + pastParticiple + other;
                    futurePerfect = "vil have " + pastParticiple + other;
                    pastConditional = "ville have " + pastParticiple + other;
                }
            } else {
                // other 17 not considered irregular cases
                if (template.startsWith("{{da-conj")
                        || template.startsWith("{{head|da|verb")
                        || template.startsWith("{{da-verb")) {
                    System.out.println("NOT COVERED VERB: " + template);
                    Constants.consideredEntriesCount--;
                } else {
                    Constants.allEntriesCount--;
                    Constants.consideredEntriesCount--;
                }
                return null;
            }
        } else if (!isRegularVerbTemplate()) {
            // Not a good entry, to ignore!
            return null;
        }

        return new DanishMorphoRuleVerb(
                imperative,
                pastParticiple,
                presentParticiple,
                presentTense,
                pastTense,
                futureTense,
                presentPerfect,
                pastPerfect,
                futurePerfect,
                presentConditional,
                pastConditional,
                passiveInfinitive,
                passiveImperative,
                passivePastParticiple,
                passivePresentParticiple,
                passivePresentTense,
                passivePastTense,
                passiveFutureTense,
                passivePresentPerfect,
                passivePastPerfect,
                passiveFuturePerfect,
                passivePresentConditional,
                passivePastConditional
        );
    }

    private boolean isRegularVerbTemplate() {
        return !(template.equals("{{head|da|verb}}")
                || template.equals("{{head|da|verbs}}")
                || template.startsWith("{{head|da|verb form")
                || template.equals("{{head|da|past participle}}")
                || template.equals("{{head|da|past participles}}")
                || template.equals("{{head|da|present participle}}")
                || template.equals("{{da-verb}}"));

    }

}
