/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class ClonarArray {

    public static ArrayList Clonar(ArrayList listaaClonar) {
        ArrayList clon = new ArrayList();
        
        for (int i = 0; i < listaaClonar.size() ; i++) {
            clon.add(listaaClonar.get(i));
        }return  clon; 
    }

}
