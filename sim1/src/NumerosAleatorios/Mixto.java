/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NumerosAleatorios;

import javax.swing.JOptionPane;

 
public class Mixto {
    private int a;
    private double X0;
    private int m;
    private int c;

    public Mixto(int a, double X0, int m, int c) {
        this.a = a;
        this.X0 = X0;
        this.m = m;
        this.c = c;
    }
    public double siguiente(){
        X0 = ((a * X0) + c) % m;
        return X0/m;
    }

    public boolean validar(){
//        inicio validando el valor de a
    if (a <= 0) {
        JOptionPane.showMessageDialog(null, "a mayor que 0");
        return false;
    } else {
        if (a % 2 == 0) {
            JOptionPane.showMessageDialog(null, "a tiene que ser impar");
            return false;
        } else {
          if (a % 3 == 0 || a % 5 == 0) {
              JOptionPane.showMessageDialog(null, "a no divisible entr 5 y 3");
              return false;
          } else {
              if(c>=a){
                  JOptionPane.showMessageDialog(null, "c menor que a");
                   return false;
              }
              if(!mcd(m,a)){
                  JOptionPane.showMessageDialog(null, "a debe ser primo relativo de m");
                   return false;
              }
//              valido el valor de Xo
              if (X0 <= 0) {
                  JOptionPane.showMessageDialog(null, "Xo mayor que 0");
                 return false;
              }
//              valido el valor de c
              if(c<=0){
                  JOptionPane.showMessageDialog(null, "c mayor que 0");
                 return false;
              }
              else
               {
               if(c % 2 ==0){
                   JOptionPane.showMessageDialog(null, "c impar");
                 return false;
               }
               else
               {
               if (c % 8 != 5) {
                   JOptionPane.showMessageDialog(null, "c debe cmplir que c mod 8=5");
                 return false;
               }
               else{
               if(!mcd(m,c)){
                   JOptionPane.showMessageDialog(null, "c y m deben ser primos relativos");
                 return false;
               }
               }
//       valido el valor de m
             if (m <= 0) {
                 JOptionPane.showMessageDialog(null, "m mayor que 0");
                 return false;
             } else {
             if (!esPrimo(m)) {
                 JOptionPane.showMessageDialog(null, "m debe ser  primo");
                 return false;
             } else {
             if(m >= Math.pow(2, 32)) {
                 JOptionPane.showMessageDialog(null, "m debe ser menor que 2^32");
                 return false;
             }
             else{
             if(a>=m || c>=m || X0>=m){
                 JOptionPane.showMessageDialog(null, "m debe ser mayor que las otras variables");
                 return false;
             }
             }
                 return true;
             }
             }
             }
             }
            }
          }
        }
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
    public static boolean esPrimo(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
