/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.lcl.wimmp.da;

import it.uniroma1.lcl.wimmp.MorphoForm;
import it.uniroma1.lcl.wimmp.MorphoRule;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author michele
 */
public class DanishMorphoRulePronoun implements MorphoRule {

    private final List<MorphoForm> list;

    // For personal pronouns
    public DanishMorphoRulePronoun(
            String accusative,
            String possessiveCommon,
            String possessiveNeuter,
            String possessivePlural,
            String reflexive
    ) {
        list = new LinkedList<>();

        if (accusative != null) {
            list.add(new MorphoForm(accusative, Constants.accusative_da));
        }
        if (possessiveCommon != null) {
            list.add(new MorphoForm(possessiveCommon, Constants.possessiveCommon_da));
        }
        if (possessiveNeuter != null) {
            list.add(new MorphoForm(possessiveNeuter, Constants.possessiveNeuter_da));
        }
        if (possessivePlural != null) {
            list.add(new MorphoForm(possessivePlural, Constants.possessivePlural_da));
        }
        if (reflexive != null) {
            list.add(new MorphoForm(reflexive, Constants.reflexive_da));
        }
    }

    // Some kinds of pronouns like demonstrative, indefinite, other
    public DanishMorphoRulePronoun(
            String singularNeuter,
            String plural
    ) {
        list = new LinkedList<>();

        if (singularNeuter != null) {
            list.add(new MorphoForm(singularNeuter, Constants.singularNeuter_da));
        }
        if (plural != null) {
            list.add(new MorphoForm(plural, Constants.plural_da));
        }
    }
    
    public DanishMorphoRulePronoun(){
        list = new LinkedList<>();
    }
    
    @Override
    public List<MorphoForm> getForms() {
        return list;
    }

}
