/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import NumerosAleatorios.Distribucion;
import Vista.VistaINt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author pc
 */
public abstract class BaseJugador extends Thread {

    protected String JugadorNombre;
    protected int tiempoEspera;
    protected boolean esperando;
    protected int InventariadoAcutal;
    protected int inventariominimo;
    protected String TipoEntidad;
    public ArrayList<ArrayList> ListasNumerosAleatoris = new ArrayList();

    protected int PrecioDeVentaUnidad = 7;
    protected int PrecioDeCompraUnidad = 5;
    ArrayList<RegistroJugador> minoristas = new ArrayList();
    protected String ip;
    protected int Puerto;
    protected int dias = 1;
    Listener l;
    Vista.VistaINt log;
    protected int CantidadaPedir;

    public ArrayList<Pedido> ListaPedidos = new ArrayList();
    public ArrayList<Pedido> Pedidosperdidos = new ArrayList();

    public abstract void RecibirPedido(Pedido p);

    public abstract void RecibirPaquete(Paquete p);

    public abstract void RecibirMinorista(RegistroJugador r);

    public abstract void RecibirJugada(Jugada j);

    public abstract void Registrar(ValidarRegistro v);

    public abstract void RestarDias();

    public void Iterar() {
        dias++;

    }

    public VistaINt getLog() {

        return log;
    }

    public void setLog(VistaINt log) {
        this.log = log;
    }

    public BaseJugador(Vista.VistaINt log, String JugadorNombre, int tiempoEspera, boolean esperando, int InventariadoAcutal, int inventariominimo, int PrecioDeVentaUnidad, String ip, int Puerto) throws IOException {

        this.JugadorNombre = JugadorNombre;
        this.tiempoEspera = tiempoEspera;
        this.esperando = esperando;
        this.InventariadoAcutal = InventariadoAcutal;
        this.inventariominimo = inventariominimo;

        this.PrecioDeVentaUnidad = PrecioDeVentaUnidad;
        this.ip = ip;
        this.Puerto = Puerto;
        this.log = log;
        this.l = new Listener(this);

    }

    public String getJugadorNombre() {
        return JugadorNombre;
    }

    public boolean validarRepeticion(RegistroJugador n) {

        for (RegistroJugador minorista : minoristas) {
            if (minorista.getNombre().equals(n.Nombre)) {
                log.EscribirLog("Error Al Registrar Minoristas :Ya esta Registrado ese Nombre");
                return false;
            }
        }
        return true;
    }

    public void setJugadorNombre(String JugadorNombre) {
        this.JugadorNombre = JugadorNombre;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public boolean isEsperando() {
        return esperando;
    }

    public void setEsperando(boolean esperando) {
        this.esperando = esperando;
    }

    public int getInventariadoAcutal() {
        return InventariadoAcutal;
    }

    public void setInventariadoAcutal(int InventariadoAcutal) {
        this.InventariadoAcutal = InventariadoAcutal;
    }

    public int getInventariominimo() {
        return inventariominimo;
    }

    public void setInventariominimo(int inventariominimo) {
        this.inventariominimo = inventariominimo;
    }

    public int getPrecioDeVentaUnidad() {
        return PrecioDeVentaUnidad;
    }

    public void setPrecioDeVentaUnidad(int PrecioDeVentaUnidad) {
        this.PrecioDeVentaUnidad = PrecioDeVentaUnidad;
    }

    public ArrayList<Pedido> getListaPedidos() {
        return ListaPedidos;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPuerto() {
        return Puerto;
    }

    public void setPuerto(int Puerto) {
        this.Puerto = Puerto;
    }

    protected int TraerDemandadiaria() {
        int res = Distribucion.DemandaDiaria((float) ListasNumerosAleatoris.get(0).get(0));
        float r = (float) ListasNumerosAleatoris.get(0).get(0);
        ListasNumerosAleatoris.get(0).remove(0);
        ListasNumerosAleatoris.get(0).add(r);
   System.out.println("ASDasd:"+res);
        return res;
    }
    
     public int TraerTiempoEspera() {
        int res = Distribucion.DemandaDiaria((float) ListasNumerosAleatoris.get(1).get(0));
        float r = (float) ListasNumerosAleatoris.get(0).get(0);
        ListasNumerosAleatoris.get(0).remove(0);
        ListasNumerosAleatoris.get(0).add(r);
   System.out.println("ASDasd:"+res);
        return res;
    }
      protected int TraerTiempoEntrega() {
        int res = Distribucion.DemandaDiaria((float) ListasNumerosAleatoris.get(2).get(0));
        float r = (float) ListasNumerosAleatoris.get(0).get(0);
        ListasNumerosAleatoris.get(0).remove(0);
        ListasNumerosAleatoris.get(0).add(r);
          System.out.println("ASDasd:"+res);
        return res;
    }

}
