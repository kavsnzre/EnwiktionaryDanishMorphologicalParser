/**
 *
 */
package it.uniroma1.lcl.wimmp.da;

import it.uniroma1.lcl.wimmp.MorphoEntry;
import it.uniroma1.lcl.wimmp.MorphoEntry.POS;
import it.uniroma1.lcl.wimmp.da.pos.Adjective;
import it.uniroma1.lcl.wimmp.da.pos.Letter;
import it.uniroma1.lcl.wimmp.da.pos.NoFlectionPOS;
import it.uniroma1.lcl.wimmp.da.pos.Noun;
import it.uniroma1.lcl.wimmp.da.pos.Pronoun;
import it.uniroma1.lcl.wimmp.da.pos.Verb;
import java.util.AbstractMap.SimpleEntry;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DanishPatterns {

    private final String lemma;
    private final Matcher matcher_line;
    List<MorphoEntry> localMyMorphoEntries;

    Stack<SimpleEntry<String, String>> stackNouns; // please see below
    Stack<SimpleEntry<String, String>> stackVerbs; // please see below

    public DanishPatterns(String lemma, String inputTagText) {
        this.lemma = lemma;
        this.matcher_line = Pattern.compile("^(.*)$", Pattern.MULTILINE).matcher(inputTagText);
        this.localMyMorphoEntries = new LinkedList<>();

        this.stackNouns = new Stack<>();
        this.stackVerbs = new Stack<>();
    }

    public boolean isDanish() {
        while (matcher_line.find()) {
            if (Pattern.compile("^(=)+Danish(=)+$").matcher(matcher_line.group(1)).find()) {
                return true;
            }
        }
        return false;
    }

    public List<MorphoEntry> checkMorphoEntry() {
        String line;
        Matcher app;
        String posRegex = "("
                + "^=+Noun=+$"
                + "|^=+Proper noun=+$"
                + "|^=+Numeral=+$"
                + "|^=+Inflection=+$"
                + "|^=+Declension=+$"
                + "|^=+Verb=+$"
                + "|^=+Adjective=+$"
                + "|^=+Adverb=+$"
                + "|^=+Pronoun=+$"
                + "|^=+Article=+$"
                + "|^=+Conjunction=+$"
                + "|^=+Interjection=+$"
                + "|^=+Preposition=+$"
                // ----- ADDED in MorphoEntry
                + "|^=+Conjugation=+$"
                + "|^=+Particle=+$"
                + "|^=+Letter=+$"
                + "|^=+Acronym=+$"
                + "|^=+Abbreviation=+$"
                + "|^=+Prefix=+$"
                + "|^=+Suffix=+$"
                + ")";
        while (matcher_line.find()) {

            line = matcher_line.group(1);

            app = Pattern.compile(posRegex).matcher(line);
            if (app.find() && matcher_line.find()) {
                String pos = app.group(1).replaceAll("=", "");
                String template = matcher_line.group(1);
                switch (pos) {
                    case "Proper noun":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.PROPER_NOUN, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Numeral":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.NUMERAL, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Adjective":
                        Adjective adjective = new Adjective(lemma, template);
                        DanishMorphoRuleAdjective ruleAdjective = adjective.createAllFlections();
                        if (ruleAdjective != null) {
                            Constants.allLemmasPlusForms += 1 + (ruleAdjective.getForms()).size();
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.ADJECTIVE, ruleAdjective));
                        }
                        break;
                    case "Adverb":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.ADVERB, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Article":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.ARTICLE, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Conjunction":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.CONJUNCTION, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Interjection":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.INTERJECTION, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Preposition":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.PREPOSITION, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Particle":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.PARTICLE, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Letter":
                        Letter letter = new Letter(lemma, template);
                        DanishMorphoRuleLetter ruleLetter = letter.createAllFlections();
                        if (ruleLetter != null) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.LETTER, ruleLetter));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms += 2;
                            System.out.println(template);
                        }
                        break;
                    case "Acronym":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.ACRONYM, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Abbreviation":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.ABBREVIATION, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Prefix":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.PREFIX, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    case "Suffix":
                        if (NoFlectionPOS.checkTemplate(template)) {
                            localMyMorphoEntries.add(new MorphoEntry(lemma, POS.SUFFIX, new DanishMorphoRuleNoFlections()));
                            Constants.allEntriesCount++;
                            Constants.consideredEntriesCount++;
                            Constants.allLemmasPlusForms++;
                            System.out.println(template);
                        }
                        break;
                    default:
                        // We have to analyze only "Noun", "Inflection", "Declension"
                        // and "Verb", "Conjugation"
                        // Here we just create a stack of entries (we are obbligated!), and after
                        // the exit from the while loop we'll insert these entries into the file
                        if (pos.equals("Noun")) {
                            if (lemma.equals("beg.")) {
                                localMyMorphoEntries.add(new MorphoEntry(lemma, POS.ABBREVIATION, new DanishMorphoRuleNoFlections()));
                                Constants.allEntriesCount++;
                                Constants.consideredEntriesCount++;
                                Constants.allLemmasPlusForms++;
                                System.out.println(template);
                            } else {
                                stackNouns.push(new SimpleEntry(lemma, template));
                            }
                        } else if (pos.equals("Inflection") || pos.equals("Declension")) {
                            if (template.startsWith("{{da-noun")) {
                                if (!stackNouns.empty() && ((stackNouns.peek()).getKey()).equals(lemma)) {
                                    // Even some letters have "=Inflection=", and so we have
                                    // to analyse the lemma

                                    if (template.equals("{{da-noun-infl-base")) {
                                        String fragment;
                                        do {
                                            matcher_line.find();
                                            fragment = matcher_line.group(1);
                                            template = template + fragment;
                                        } while (!fragment.equals("}}"));
                                        /* e.g.
                                    ====Inflection====
                                    {{da-noun-infl-base
                                    |sg-indef=musical
                                    |sg-def=musicalen
                                    |sg-def-2=
                                    |pl-indef=musicaler
                                    |pl-indef-2=musicals
                                    |pl-def=musicalerne
                                    |pl-def-2=
                                    |gen-sg-indef=musicals
                                    |gen-sg-def=musicalens
                                    |gen-sg-def-2=
                                    |gen-pl-indef=musicalers
                                    |gen-pl-indef-2=musicals'
                                    |gen-pl-def=musicalernes
                                    |gen-pl-def-2=
                                    |g=c
                                    }}
                                         */
                                    }
                                    stackNouns.pop();
                                    stackNouns.push(new SimpleEntry(lemma, template));
                                }
                            }
                        }

                        if (pos.equals("Verb")) {
                            stackVerbs.push(new SimpleEntry(lemma, template));
                        } else if (pos.equals("Conjugation")) {
                            // we are sure that the previous element in the stack
                            // is a templete following the label "=Verb=" of the 
                            // current verbal entry
                            if (template.matches("\\{\\{da-(conj|verb)(.*)\\}\\}")) {
                                stackVerbs.pop();
                                stackVerbs.push(new SimpleEntry(lemma, template));
                            }
                        }
                        break;
                }
            }
        }

        // Now we can add nouns
        Noun noun;
        DanishMorphoRuleNoun ruleNoun;
        for (SimpleEntry<String, String> nounEntry : stackNouns) {
            noun = new Noun(nounEntry.getKey(), nounEntry.getValue());
            ruleNoun = noun.createAllFlections();
            if (ruleNoun != null) {
                localMyMorphoEntries.add(new MorphoEntry(lemma, POS.NOUN, ruleNoun));
                Constants.allLemmasPlusForms += 1 + (ruleNoun.getForms()).size();
            }

        }

        // Now we can add verbs
        Verb verb;
        DanishMorphoRuleVerb ruleVerb;
        for (SimpleEntry<String, String> verbEntry : stackVerbs) {
            verb = new Verb(verbEntry.getKey(), verbEntry.getValue());
            ruleVerb = verb.createAllFlections();
            if (ruleVerb != null) {
                localMyMorphoEntries.add(new MorphoEntry(lemma, POS.VERB, ruleVerb));
                Constants.allLemmasPlusForms += 1 + (ruleVerb.getForms()).size();
            }

        }

        if (!localMyMorphoEntries.isEmpty()) {
            System.out.println(localMyMorphoEntries.size());
        }

        return localMyMorphoEntries;
    }

    public static List<MorphoEntry> getAllPronouns() {
        return Pronoun.createAllPronounsInflections();
    }
}
