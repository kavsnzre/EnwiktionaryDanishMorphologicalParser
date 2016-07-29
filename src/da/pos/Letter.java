/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.lcl.wimmp.da.pos;

import it.uniroma1.lcl.wimmp.da.DanishMorphoRuleLetter;

/**
 *
 * @author michele
 */
public class Letter {

    private final String lemma;
    private String template;

    public Letter(String lemma, String template) {
        this.lemma = lemma;
        this.template = template;
    }

    public DanishMorphoRuleLetter createAllFlections() {
        String upperCase = null;

        if (isRegulareLetterTemplate()) {
            if ((lemma.toLowerCase()).equals(lemma)) {
                upperCase = lemma.toUpperCase();
                return new DanishMorphoRuleLetter(upperCase);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean isRegulareLetterTemplate() {
        return template.startsWith("{{da-letter|")
                || template.startsWith("{{head|da|letter");
    }

}
