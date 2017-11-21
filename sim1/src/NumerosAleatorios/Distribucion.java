/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NumerosAleatorios;

import java.util.ArrayList;

public class Distribucion {

    /**
     *
     * @param num NUMERO PSEUDOALEATORIO
     * @param l LANDA
     * @return
     */
    public static float MetodoExponecial(float num, int l) {
        if (num == 0) {
            return 0;
        } else {
            float salida = 0;
            salida = (float) (-l * Math.log(num));
            return salida;
        }
    }

    /**
     *
     * @param intervalos numero de intervlaos a crear
     * @param pseudo numero pseudolaeatorio
     * @return
     */
    public static int Intervalos(int intervalos, double pseudo) {

        for (int i = 0; i < intervalos; i++) {
            //  System.err.println(((100 / intervalos)) * i + "|" + ((100 / intervalos)) * (i + 1));
            if (pseudo * 100 >= (100 / intervalos) * i && pseudo * 100 < (100 / intervalos) * (i + 1)) {
                return i + 1;
            }

        }

        return 0;

    }

    /**
     *
     * @param num nuemero pseudoAleatorio
     * @param a min
     * @param b max
     * @return
     */
    public static float MetodoUniforme(float num, int a, int b) {
        float salida = 0;

        salida = (float) (a + (b - a) * num);

        return salida;
    }

    public static int DemandaDiaria(float num) {
        float[][] m = {
            {0, 0.02f, 25},
            {0.02f, 0.04f, 34},
            {0.04f, 0.08f, 26},
            {0.08f, 0.13f, 33},
            {0.13f, 0.19f, 27},
            {0.19f, 0.29f, 32},
            {0.29f, 0.41f, 28},
            {0.41f, 0.56f, 31},
            {0.56f, 0.76f, 29},
            {0.76f, 1, 20}
        };

        for (int i = 0; i < m.length; i++) {

            if (m[i][0] <= num && num < m[i][2]) {

                return (int)m[i][2];
            }

        }
        return -1;

    }

    public static int TiempoEntrega(float num) {
        float[][] m = {
            {0, 0.2f, 1},
            {0.2f, 0.45f, 3},
            {0.45f, 0.7f, 4},
            {0.7f, 1, 2}
        };

        for (int i = 0; i < m.length; i++) {

            if (m[i][0] <= num && num < m[i][2]) {

                return (int)m[i][2];
            }

        }
        return -1;

    }

    public static int TiempoEspera(float num) {
        float[][] m = {
            {0, 0.01f, 4},
            {0.1f, 0.25f, 2},
            {0.25f, 0.4f, 3},
            {0.4f, 0.6f, 1},
            {0.06f, 0.1f, 0}
        };

        for (int i = 0; i < m.length; i++) {

            if (m[i][0] <= num && num < m[i][2]) {

                return (int)m[i][2];
            }

        }
        return -1;

    }

    public static int factorial(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }
}
