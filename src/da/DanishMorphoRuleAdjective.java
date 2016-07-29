/**
 *
 */
package it.uniroma1.lcl.wimmp.da;

import it.uniroma1.lcl.wimmp.MorphoForm;
import it.uniroma1.lcl.wimmp.MorphoRule;

import java.util.LinkedList;
import java.util.List;

public class DanishMorphoRuleAdjective implements MorphoRule {

    private final List<MorphoForm> list;

    public DanishMorphoRuleAdjective(
            String singularNeuter,
            String singularDefiniteAndPlural,
            String comparative,
            String superlative
            ) {

        list = new LinkedList<>();

        if (singularNeuter != null) {
            list.add(new MorphoForm(singularNeuter, Constants.singularNeuter_da));
        }
        if (singularDefiniteAndPlural != null) {
            list.add(new MorphoForm(singularDefiniteAndPlural, Constants.singularDefiniteAndPlural_da));
        }
        if (comparative != null) {
            list.add(new MorphoForm(comparative, Constants.comparative_da));
        }
        if (superlative != null) {
            list.add(new MorphoForm(superlative, Constants.superlative_da));
        }
    }

    @Override
    public List<MorphoForm> getForms() {
        return list;
    }

}
