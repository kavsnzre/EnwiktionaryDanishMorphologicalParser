/**
 *
 */
package it.uniroma1.lcl.wimmp.da;

import it.uniroma1.lcl.wimmp.MorphoForm;
import it.uniroma1.lcl.wimmp.MorphoRule;

import java.util.LinkedList;
import java.util.List;

public class DanishMorphoRuleNoun implements MorphoRule {

    private final List<MorphoForm> list;
    
    public DanishMorphoRuleNoun(
            String singularDefinite,
            String pluralIndefinite,
            String pluralDefinite,
            String genitiveSingularIndefinite,
            String genitiveSingularDefinite,
            String genitivePluralIndefinite,
            String genitivePluralDefinite
            ) {
        
        list = new LinkedList<>();
        
        if (singularDefinite != null) {
            list.add(new MorphoForm(singularDefinite, Constants.singularDefinite_da));
        }
        if (pluralIndefinite != null) {
            list.add(new MorphoForm(pluralIndefinite, Constants.pluralIndefinite_da));
        }
        if (pluralDefinite != null) {
            list.add(new MorphoForm(pluralDefinite, Constants.pluralDefinite_da));
        }
        if (genitiveSingularIndefinite != null) {
            list.add(new MorphoForm(genitiveSingularIndefinite, Constants.genitiveSingularIndefinite_da));
        }
        if (genitiveSingularDefinite != null) {
            list.add(new MorphoForm(genitiveSingularDefinite, Constants.genitiveSingularDefinite_da));
        }
        if (genitivePluralIndefinite != null) {
            list.add(new MorphoForm(genitivePluralIndefinite, Constants.genitivePluralIndefinite_da));
        }
        if (genitivePluralDefinite != null) {
            list.add(new MorphoForm(genitivePluralDefinite, Constants.genitivePluralDefinite_da));
        }
    }

    @Override
    public List<MorphoForm> getForms() {
        return list;
    }

}
