/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author pc
 */
public class ValidarRegistro extends Paquete_envio {

    boolean res;
    String ip;
    int port;
    String nombre;

    public ValidarRegistro(boolean res, int tipo) {
        this.Tipo = tipo;
        this.res = res;

    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + "ip: " + ip + "puerto" + port; //To change body of generated methods, choose Tools | Templates.
    }

}
