/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.lcl.wimmp.da.pos;

import it.uniroma1.lcl.wimmp.MorphoEntry;
import it.uniroma1.lcl.wimmp.MorphoEntry.POS;
import it.uniroma1.lcl.wimmp.da.DanishMorphoRulePronoun;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author michele
 */
public class Pronoun {

    public static List<MorphoEntry> createAllPronounsInflections() {

        LinkedList<MorphoEntry> pronouns = new LinkedList<>();

        // Personal Pronouns
        pronouns.add(new MorphoEntry("jeg", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "mig",
                        "min",
                        "mit",
                        "mine",
                        "mig"
                )));
        pronouns.add(new MorphoEntry("du", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "dig",
                        "din",
                        "dit",
                        "dine",
                        "dig"
                )));
        pronouns.add(new MorphoEntry("De", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "Dem",
                        "Deres",
                        "Deres",
                        "Deres",
                        "Dem"
                )));
        pronouns.add(new MorphoEntry("den", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "den",
                        "dens/sit",
                        "dets/sit",
                        "dens/sine",
                        "sig"
                )));
        pronouns.add(new MorphoEntry("det", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "det",
                        "dens/sin",
                        "dets/sit",
                        "dets/sine",
                        "sig"
                )));
        pronouns.add(new MorphoEntry("De", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "Dem",
                        "Deres",
                        "Deres",
                        "Deres",
                        "Dem"
                )));
        pronouns.add(new MorphoEntry("han", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "ham",
                        "hans/sin",
                        "hans/sit",
                        "hans/sine",
                        "sig"
                )));
        pronouns.add(new MorphoEntry("hun", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "hende",
                        "hendes/sin",
                        "hendes/sit",
                        "hendes/sine",
                        "sig"
                )));
        pronouns.add(new MorphoEntry("vi", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "os",
                        "vores/vor",
                        "vores/vort",
                        "vores/vore",
                        "os"
                )));
        pronouns.add(new MorphoEntry("I", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "jer",
                        "jeres/jer",
                        "jeres/jert",
                        "jeres/jere",
                        "jer"
                )));
        pronouns.add(new MorphoEntry("de", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "dem",
                        "deres",
                        "deres",
                        "deres",
                        "sig"
                )));
//60
        // Demonstrative Pronouns
        pronouns.add(new MorphoEntry("denne", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "dette",
                        "disse"
                )));
        pronouns.add(new MorphoEntry("den", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "det",
                        "de"
                )));
        // Indefinite Pronouns
        pronouns.add(new MorphoEntry("al", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "alt/alting",
                        "alle"
                )));
        pronouns.add(new MorphoEntry("hver", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "hvert",
                        null
                )));
        pronouns.add(new MorphoEntry("ingen", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "intet/intenting",
                        "ingen"
                )));
        pronouns.add(new MorphoEntry("denne", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "dette",
                        "disse"
                )));
        pronouns.add(new MorphoEntry("nogen", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "noget",
                        "nogle/nogen"
                )));
        pronouns.add(new MorphoEntry("anden", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        null,
                        "andre"
                )));
        pronouns.add(new MorphoEntry("en/én", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "et/ét",
                        "ene"
                )));
        pronouns.add(new MorphoEntry("man", POS.PRONOUN, new DanishMorphoRulePronoun()));
// 26
        // Relative pronouns
        pronouns.add(new MorphoEntry("hvilken", POS.PRONOUN,
                new DanishMorphoRulePronoun(
                        "hvilket",
                        "hvilke"
                )));
        pronouns.add(new MorphoEntry("der", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("som", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("hvis", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("hvem", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("hvad", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("hvo", POS.PRONOUN, new DanishMorphoRulePronoun()));
        
        // Other pronouns (maybe some og these belong to the previous categories) 
        pronouns.add(new MorphoEntry("allesammen/alle sammen", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("hveandre", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("niks/nix", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("nul", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("hin", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("hvad", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("begge", POS.PRONOUN, new DanishMorphoRulePronoun()));
        pronouns.add(new MorphoEntry("selv", POS.PRONOUN, new DanishMorphoRulePronoun()));
// 17
        return pronouns;
    }

}
