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
public class PedidoMayorista {
    int cantidad;
    int tiempoEspera;

    public PedidoMayorista(int cantidad, int tiempoEspera) {
        this.cantidad = cantidad;
        this.tiempoEspera = tiempoEspera;
    }

    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
    
    
    
    
}
