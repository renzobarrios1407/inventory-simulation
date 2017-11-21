/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class Pedido extends Paquete_envio implements Serializable {

    private String NombreSolicitante;
    private String IPSolicitante;
    private int PuertoSolicitante;
    private String nombre_Proveed;
    private int TiempoEspera;

    public int getTiempoEspera() {
        return TiempoEspera;
    }

    public void setTiempoEspera(int TiempoEspera) {
        this.TiempoEspera = TiempoEspera;
    }

    public boolean isPedido() {
        return Pedido;
    }

    public void setPedido(boolean Pedido) {
        this.Pedido = Pedido;
    }
    boolean Pedido;

    public String getNombre_Proveed() {
        return nombre_Proveed;
    }

    public void setNombre_Proveed(String nombre_Proveed) {
        this.nombre_Proveed = nombre_Proveed;
    }

    public String getNombreSolicitante() {
        return NombreSolicitante;
    }

    public void setNombreSolicitante(String NombreSolicitante) {
        this.NombreSolicitante = NombreSolicitante;
    }

    public String getIPSolicitante() {
        return IPSolicitante;
    }

    public void setIPSolicitante(String IPSolicitante) {
        this.IPSolicitante = IPSolicitante;
    }

    private Proovedor Proovedor;
    private int cantidad;
    private int prioridad;

    public Pedido(boolean  pedido, String NombreSolicitante, String IPSolicitante, int PuertoSolicitante, Proovedor Proovedor, int cantidad, int prioridad, int tipo, int tiempoEspera) {
        this.NombreSolicitante = NombreSolicitante;
        this.IPSolicitante = IPSolicitante;
        this.PuertoSolicitante = PuertoSolicitante;
        this.Proovedor = Proovedor;
        this.cantidad = cantidad;
        this.prioridad = prioridad;
        this.Tipo = tipo;
        this.TiempoEspera = tiempoEspera;
        this.Pedido=pedido;
    }

    public int getPuertoSolicitante() {
        return PuertoSolicitante;
    }

    public void setPuertoSolicitante(int PuertoSolicitante) {
        this.PuertoSolicitante = PuertoSolicitante;
    }

    public int getPrioridad() {

        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Proovedor getProovedor() {
        return Proovedor;
    }

    public void setProovedor(Proovedor Proovedor) {
        this.Proovedor = Proovedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
