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
public class DanishMorphoRuleNoFlections implements MorphoRule{
    private final List<MorphoForm> list;

    public DanishMorphoRuleNoFlections(){
        list = new LinkedList<>();
    }
    
    @Override
    public List<MorphoForm> getForms() {
        return list;
    }
    
}
