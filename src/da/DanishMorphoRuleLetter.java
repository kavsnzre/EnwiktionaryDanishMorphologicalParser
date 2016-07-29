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
public class DanishMorphoRuleLetter implements MorphoRule {

    private final List<MorphoForm> list;

    public DanishMorphoRuleLetter(String upperCase) {

        list = new LinkedList<>();

        if (upperCase != null) {
            list.add(new MorphoForm(upperCase, Constants.upperCase_da));
        }
    }

    @Override
    public List<MorphoForm> getForms() {
        return list;
    }

}
