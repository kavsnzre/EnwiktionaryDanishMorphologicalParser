/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.lcl.wimmp.da.pos;

/**
 *
 * @author michele
 */
public class NoFlectionPOS {
    
    public static boolean checkTemplate(String template){
        return template.matches("\\{\\{(head\\|da\\||da-(prep|letter))(.*)\\}\\}");
    }
    
}
