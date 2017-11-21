/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Administrador
 */
public class PasarDia extends Paquete_envio {

    boolean mensaje = true;

    public PasarDia(int tipo, boolean mensaje) {
        this.Tipo = tipo;
        this.mensaje = mensaje;
    }

}
