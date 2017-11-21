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
public class Jugada extends Paquete_envio {

    int Estado;//1esperando;2enviadoPedido;3pasar
    String Nombre;

    public Jugada(int Estado, int tipo, String Nombre) {
        this.Estado = Estado;
        this.Nombre = Nombre;
        this.Tipo = tipo;
    }

}
