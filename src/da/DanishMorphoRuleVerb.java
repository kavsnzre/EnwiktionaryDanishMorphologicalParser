/**
 *
 */
package it.uniroma1.lcl.wimmp.da;

import it.uniroma1.lcl.wimmp.MorphoForm;
import it.uniroma1.lcl.wimmp.MorphoRule;

import java.util.LinkedList;
import java.util.List;

public class DanishMorphoRuleVerb implements MorphoRule {

    private List<MorphoForm> list;

    public DanishMorphoRuleVerb(
            String imperative,
            String pastParticiple,
            String presentParticiple,
            String presentTense,
            String pastTense,
            String futureTense,
            String presentPerfect,
            String pastPerfect,
            String futurePerfect,
            String presentConditional,
            String pastConditional,
            String passiveInfinitive,
            String passiveImperative,
            String passivePastParticiple,
            String passivePresentParticiple,
            String passivePresentTense,
            String passivePastTense,
            String passiveFutureTense,
            String passivePresentPerfect,
            String passivePastPerfect,
            String passiveFuturePerfect,
            String passivePresentConditional,
            String passivePastConditional
    ) {
        list = new LinkedList<>();
        if (imperative != null) {
            list.add(new MorphoForm(imperative, Constants.imperative_da));
        }
        if (pastParticiple != null) {
            list.add(new MorphoForm(pastParticiple, Constants.pastParticiple_da));
        }
        if (presentParticiple != null) {
            list.add(new MorphoForm(presentParticiple, Constants.presentParticiple_da));
        }
        if (presentTense != null) {
            list.add(new MorphoForm(presentTense, Constants.presentTense_da));
        }
        if (pastTense != null) {
            list.add(new MorphoForm(pastTense, Constants.pastTense_da));
        }
        if (futureTense != null) {
            list.add(new MorphoForm(futureTense, Constants.futureTense_da));
        }
        if (presentPerfect != null) {
            list.add(new MorphoForm(presentPerfect, Constants.presentPerfect_da));
        }
        if (pastPerfect != null) {
            list.add(new MorphoForm(pastPerfect, Constants.pastPerfect_da));
        }
        if (futurePerfect != null) {
            list.add(new MorphoForm(futurePerfect, Constants.futurePerfect_da));
        }
        if (presentConditional != null) {
            list.add(new MorphoForm(presentConditional, Constants.presentConditional_da));
        }
        if (pastConditional != null) {
            list.add(new MorphoForm(pastConditional, Constants.pastConditional_da));
        }

        if (passiveInfinitive != null) {
            list.add(new MorphoForm(passiveInfinitive, Constants.passiveInfinitive_da));
        }
        if (passiveImperative != null) {
            list.add(new MorphoForm(passiveImperative, Constants.passiveImperative_da));
        }
        if (passivePastParticiple != null) {
            list.add(new MorphoForm(passivePastParticiple, Constants.passivePastParticiple_da));
        }
        if (passivePresentParticiple != null) {
            list.add(new MorphoForm(passivePresentParticiple, Constants.passivePresentParticiple_da));
        }
        if (passivePresentTense != null) {
            list.add(new MorphoForm(passivePresentTense, Constants.passivePresentTense_da));
        }
        if (passivePastTense != null) {
            list.add(new MorphoForm(passivePastTense, Constants.passivePastTense_da));
        }
        if (passiveFutureTense != null) {
            list.add(new MorphoForm(passiveFutureTense, Constants.passiveFutureTense_da));
        }
        if (passivePresentPerfect != null) {
            list.add(new MorphoForm(passivePresentPerfect, Constants.passivePresentConditional_da));
        }
        if (passivePastPerfect != null) {
            list.add(new MorphoForm(passivePastPerfect, Constants.passivePastConditional_da));
        }
        if (passiveFuturePerfect != null) {
            list.add(new MorphoForm(passiveFuturePerfect, Constants.passivePresentPerfect_da));
        }
        if (passivePresentConditional != null) {
            list.add(new MorphoForm(passivePresentConditional, Constants.passivePastPerfect_da));
        }
        if (passivePastConditional != null) {
            list.add(new MorphoForm(passivePastConditional, Constants.passiveFuturePerfect_da));
        }

    }

    @Override
    public List<MorphoForm> getForms() {
        return list;
    }

}
