/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class Paquete  extends Paquete_envio implements Serializable  {
    
    private Pedido p;
    private int inventario;
    private int Precio;

    public Paquete(Pedido p,int precio,int tipo) {
        this.p = p;
        this.inventario =p.getCantidad();
        this.Precio=inventario*precio;
        this.Tipo=tipo;
        
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }
    
    

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
        this.p = p;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
    
}
