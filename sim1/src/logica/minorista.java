/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

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
public class minorista extends BaseJugador {

    boolean simulado;
    private int recursos = 500;
    private int DiasDispuestosaEsperar;
    private Proovedor provedor;
    float CostoInventarioDiario = 0.14f;
    private boolean Registrado;
    ArrayList<Jugada> jugadas = new ArrayList();

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

    public minorista(boolean simulado,
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
        ListaPedidos.add(p);
    }

    @Override
    public void RecibirPaquete(Paquete p) {
        InventariadoAcutal += p.getInventario();
        recursos -= p.getPrecio();
        esperando = false;
    }

    @Override
    public void Iterar() {
        log.ControlManual(true);
        super.Iterar();
        inventariominimo = log.GetPuntodeReorden();
        CantidadaPedir = log.GetCantidad();
        log.EscribirLog("Iterando");

        if (simulado) {
            System.out.println("aasdadasdaasd!!!!--");
            CantidadaPedir = this.TraerDemandadiaria();
            tiempoEspera = this.TraerTiempoEspera();
            EnviarPedido(CantidadaPedir, tiempoEspera);

        }
        AtenderPedido();
        RestarDias();
        PasarDia();
        recursos -= CostoInventarioDiario * InventariadoAcutal;

    }

    public void EnviarPedido(int Cantidad, int DiasTiempoEspera) {
        if (recursos > 0) {
            Comunicacion.SolicitarPedido(new Pedido(true, JugadorNombre, ip, Puerto, provedor, Cantidad, 0, 0, tiempoEspera), log);
            recursos -= Cantidad * PrecioDeCompraUnidad;
            log.EscribirLog("Se Solicito Un Pedido por el precio de " + Cantidad * PrecioDeCompraUnidad + "\n");
            EnviarJugada(2);
            esperando = true;
        } else {
            log.EscribirLog("Recursos Insuficientes" + Cantidad * PrecioDeCompraUnidad + "\n");
              EnviarJugada(3);
        }
    }

    public void EnviarJugada(int Estado) {
        Comunicacion.EnviarJugada(provedor, this.JugadorNombre, log, Estado);
        log.ControlManual(false);
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
            if (simulado) {
                CantidadaPedir = this.TraerDemandadiaria();
                tiempoEspera = this.TraerTiempoEspera();
                EnviarPedido(CantidadaPedir, tiempoEspera);

            }

        }
        log.Registrar(v.res);
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        String s = "";
        while (true) {
            s = "";
            s += "Capital Actual:" + this.recursos + "\n";
            s += "Inventario Acutal:" + this.InventariadoAcutal + "\n";
            s += "-------------------\n";
            s += "Pedidos Pendientes\n";
            for (Pedido ListaPedido : ListaPedidos) {
                s += "Solicitado Por " + ListaPedido.getNombreSolicitante() + " Cantidad: " + ListaPedido.getCantidad() + "\n";

            }
            log.EsribirVista1(s);

            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(minorista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void RecibirMinorista(RegistroJugador r) {
        if (validarRepeticion(r) && minoristas.size() < 1 && r.TipoEntidad.equals("C")) {

            minoristas.add(r);
            jugadas.add(new Jugada(0, -1, r.Nombre));
            Comunicacion.EnviarRespuestaRegistro(r, log, true, this.getIp(), this.getPuerto(), this.JugadorNombre);

        } else {
            Comunicacion.EnviarRespuestaRegistro(r, log, false, this.getIp(), this.getPuerto(), this.JugadorNombre);
        }
    }

    private void AtenderPedido() {
        for (int i = 0; i < ListaPedidos.size(); i++) {

            Pedido ped = EXtraerPrioridad();
            if (ped != null) {

                if (getInventariadoAcutal() >= ped.getCantidad()) {
                    ActualizarJugada(ped);
                    ListaPedidos.remove(ped);
                    if (ped.getCantidad() > 0) {
                        log.EscribirLog("Atendiendo Pedido de " + ped.getNombreSolicitante() + "\n");
                        ped.setNombre_Proveed(getJugadorNombre());
                        Paquete paq = new Paquete(ped, PrecioDeVentaUnidad, 1);
                        Comunicacion.EntregarPaquete(paq, log);
                        recursos += ped.getCantidad() * PrecioDeVentaUnidad;
                        log.EscribirLog("Se Envio el Pedido de " + ped.getNombreSolicitante() + "" + "\n");
                        log.EscribirLog("Unidades:" + ped.getCantidad() + "Ganancias:" + (ped.getCantidad() * PrecioDeVentaUnidad) + "\n");
                        setInventariadoAcutal(getInventariadoAcutal() - ped.getCantidad());
                    }

                }

            }
        }
    }

    private void ActualizarJugada(Pedido p) {
        for (int i = 0; i < jugadas.size(); i++) {
            if (jugadas.get(i).Nombre.equals(p.getNombreSolicitante())) {

                jugadas.get(i).Estado = 0;
            }

        }
    }

    private logica.Pedido EXtraerPrioridad() {
        int max = 0;
        Pedido maximo = null;
        for (Pedido pedido : ListaPedidos) {
            if (pedido.getPrioridad() >= max) {
                maximo = pedido;

            }

        }

        return maximo;
    }

    @Override
    public void RecibirJugada(Jugada j) {
        for (int i = 0; i < jugadas.size(); i++) {
            if (jugadas.get(i).Nombre.equals(j.Nombre)) {
                System.out.println("DebeEntrar");
                jugadas.get(i).Estado = j.Estado;
            }

        }
    }

    private void PasarDia() {
        log.EscribirLog("Enviando Señal para Pasar el  Dia al Cliente");
        for (int i = 0; i < minoristas.size(); i++) {
            RegistroJugador m = minoristas.get(i);

            Comunicacion.SeñalPasarDia(m, log);

        }
    }

    @Override
    public void RestarDias() {
        for (Pedido ListaPedido : ListaPedidos) {
            ListaPedido.setTiempoEspera(ListaPedido.getTiempoEspera() - 1);
            if (ListaPedido.getTiempoEspera() < 0) {
                Pedidosperdidos.add(ListaPedido);
                ListaPedidos.remove(ListaPedido);
            }
        }
    }

}
