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
public class cliente extends BaseJugador {
    
    boolean simulado;
    private int recursos = 500;
    private int DiasDispuestosaEsperar;
    private Proovedor provedor;
    private boolean Registrado;
    
    public boolean isSimulado() {
        return simulado;
    }
    
    public void setSimulado(boolean simulado) {
        this.simulado = simulado;
    }
    
    public int getRecursos() {
        return recursos;
    }
    
    public void setRecursos(int recursos) {
        this.recursos = recursos;
    }
    
    public int getDiasDispuestosaEsperar() {
        return DiasDispuestosaEsperar;
    }
    
    public void setDiasDispuestosaEsperar(int DiasDispuestosaEsperar) {
        this.DiasDispuestosaEsperar = DiasDispuestosaEsperar;
    }
    
    public Proovedor getProvedor() {
        return provedor;
    }
    
    public void setProvedor(Proovedor provedor) {
        this.provedor = provedor;
    }
    
    public boolean isRegistrado() {
        return Registrado;
    }
    
    public void setRegistrado(boolean Registrado) {
        this.Registrado = Registrado;
    }
    
    public cliente(boolean simulado,
            int recursos,
            int DiasDispuestosaEsperar,
            Proovedor provedor,
            VistaINt log,
            String JugadorNombre,
            int tiempoEspera,
            boolean esperando,
            int InventariadoAcutal,
            int inventariominimo,
            int PrecioDeVentaUnidad,
            String ip,
            int Puerto) throws IOException {
        super(log, JugadorNombre, tiempoEspera, esperando, InventariadoAcutal, inventariominimo, PrecioDeVentaUnidad, ip, Puerto);
        this.simulado = simulado;
        this.recursos = recursos;
        this.DiasDispuestosaEsperar = DiasDispuestosaEsperar;
        this.provedor = provedor;
        
        this.start();
    }
    
    @Override
    public void RecibirPedido(logica.Pedido p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void RecibirPaquete(Paquete p) {
        log.EscribirLog("Paquete Recibido  de " + p.getP().getNombre_Proveed() + "Cantidad" + p.getInventario());
        InventariadoAcutal += p.getInventario();
        recursos -= p.getPrecio();
        esperando = false;
    }
    
    @Override
    public void Iterar() {
        super.Iterar();
        log.EscribirLog("Iterando");
        CantidadaPedir = this.TraerDemandadiaria();
        tiempoEspera = this.TraerTiempoEspera();
//        if (esperando) {
//            DiasDispuestosaEsperar--;
//            EnviarJugada(1);//esperando
//            if (DiasDispuestosaEsperar < 0) {
//                EnviarPedidoComoPerdida();
//            }
//
//        }
        if (simulado) {
            EnviarPedido(CantidadaPedir, tiempoEspera);
        }
    }
    
    public void EnviarPedido(int Cantidad, int DiasTiempoEspera) {
        
        Comunicacion.SolicitarPedido(new Pedido(true, JugadorNombre, ip, Puerto, provedor, Cantidad, 0, 0, tiempoEspera), log);
        EnviarJugada(2);
        log.EscribirLog("Pedido Enviado al Minorista Cantindad:"+Cantidad+ "  Dias Tiempo Espera "+DiasTiempoEspera);
        esperando = true;
        
    }
    
    public void EnviarJugada(int Estado) {
        Comunicacion.EnviarJugada(provedor, this.JugadorNombre, log, Estado);
    }
    
    private void EnviarPedidoComoPerdida() {
        esperando = false;
    }
    
    @Override
    public void Registrar(ValidarRegistro v) {
        if (v.res) {
            this.Registrado = true;
            provedor = new Proovedor(v.nombre, v.ip, v.port);
            log.EscribirLog("Se Registro el Proovedor: " + v.nombre + "\n");
            System.out.println(v.toString());
            
        }
        log.Registrar(v.res);
    }
    
    @Override
    public void run() {
        
        String s = "";
        while (true) {
            s = "";
            s += "Recursos:" + this.recursos + "\n";
            s += "Esperando:" + this.esperando + "\n";
            log.EsribirVista1(s);
            
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void RecibirMinorista(RegistroJugador r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void RecibirJugada(Jugada j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void RestarDias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
