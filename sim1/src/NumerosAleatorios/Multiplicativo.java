/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NumerosAleatorios;

import javax.swing.JOptionPane;
 
public class Multiplicativo {
    private int a;
    private double X0;
    private int m;

    public Multiplicativo(int a, double X0, int m) {
        this.a = a;
        this.X0 = X0;
        this.m = m;
    }

    public double siguienteMultiplicativo(){
         X0 = (a * X0) % m;
         return X0/m;
    }

    public  boolean validar(){
//validamos valores de A
        if (a <= 0) {
            JOptionPane.showMessageDialog(null, "a debe ser > 0");
           return false;
        }else if ( ! ( (a+3)%8==0 || (a-3)%8==0 ) ) {
                 JOptionPane.showMessageDialog(null, "a - debe existir un t, tal que A = 8t +/- 3.");
                 return false;
           }else
          // validamos Xo
                if (X0 <= 0) {
                    JOptionPane.showMessageDialog(null,"Xo debe >=0");
                     return false;
                }else
                     if (!mcd(m, (int)X0)) {
                         JOptionPane.showMessageDialog(null,"m y Xo deben ser primos relativos");
                        return !true;
                     }else
                        if ( X0%2 == 0 ) {
                            JOptionPane.showMessageDialog(null,"Xo debe ser impar");
                            return false;
                        }else
                        //Validamos el valor de m
                        if (m <= a) {
                            JOptionPane.showMessageDialog(null,"a tiene que ser menor que m");
                            return false;
                        }else
                         if (m <= X0) {
                             JOptionPane.showMessageDialog(null,"Xo tiene que ser menor que m");
                            return false;
                         }else
                            if ( m%2 != 0 ) {
                                JOptionPane.showMessageDialog(null,"m tiene que ser par");
                            return  false;
                            }else
                                 return true;
    }
    public static boolean mcd(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        if (a == 1) {
            return true;
        }
        return false;
    }
}
